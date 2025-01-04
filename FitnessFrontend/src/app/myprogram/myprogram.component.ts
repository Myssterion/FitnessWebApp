import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Program } from '../model/program.model';
import { Category } from '../model/category.model';
import { Difficulty } from '../model/difficulty.model';
import { CategoryService } from '../service/category.service';
import { DifficultyService } from '../service/difficulty.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { MyprogramService } from './myprogram.service';
import { AuthService } from '../service/auth.service';
import { UserInfo } from '../model/userInfo.model';
import { AttributeService } from '../service/attribute.service';
import { Attribute } from '../model/attribute.model';

@Component({
  selector: 'app-myprogram',
  templateUrl: './myprogram.component.html',
  styleUrl: './myprogram.component.css'
})
export class MyprogramComponent {

  isAddingMode: boolean = false;
  mode: string = "";
  program: Program = new Program();
  categories: Category[] = [];
  attributes: Attribute[] = [];
  difficulties: Difficulty[] = [];
  

  selectedFiles: File[] = [];
  previews: string[] = [];
  imagesForm: FormData = new FormData();
  programId: number = -1;

  constructor(private route: ActivatedRoute, private categoryService: CategoryService,
    private difficultyService: DifficultyService, private myprogramService: MyprogramService,
    private router: Router, private authService: AuthService, private attributeService: AttributeService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.loadValues();
      let user = this.authService.getUser();
      if(user.id)
        this.program.instructor = new UserInfo(user.id);
      this.mode = params['mode']; 
      if(this.mode === 'add')
        this.isAddingMode = true;
      if(this.mode === 'edit'){
        this.isAddingMode = false;
        this.route.queryParams.subscribe(queryParams =>{
          this.programId = queryParams['id'];
          this.loadProgram(this.programId);
        })
      }
    });
  }

  loadProgram(programId: number) {
    this.myprogramService.getProgramById(programId).subscribe(
      (response: Program) => {
        this.program = response;
        if(this.program && this.program.category && this.program.category.id)
        this.loadAttributes(this.program.category.id);
        if(this.program.pictures != null) {
          this.program.pictures.forEach(p => {
            this.previews.push("http://localhost:8080" + p);
          })
        }

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }


  private loadValues(){
    this.categoryService.getCategories().subscribe(
      (response: Category[]) => {
        this.categories = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )

    this.difficultyService.getDifficulties().subscribe(
      (response: Difficulty[]) => {
        this.difficulties = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )


  }

  onCategoryChange() {
    if(this.program.category?.id)
      this.loadAttributes(this.program.category.id);
  }

  loadAttributes(categoryId: number) {
    this.attributeService.getAttributes(categoryId).subscribe(
      (response: Attribute[]) => {
        this.attributes = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onSubmit() {
    var dataForm = new FormData();
    if(this.selectedFiles)
      this.fillDataForm(dataForm,this.selectedFiles);
    
    if(this.isAddingMode) {
      this.myprogramService.addProgram(this.program).subscribe(
        (response: Program) => {
          this.program = response;
          if(this.program.id !== null) {
            this.myprogramService.uploadImagesForProgram(dataForm,this.program.id).subscribe(
              (response: HttpResponse<any>) => {
                if(response.status === 200) {
                  this.router.navigate(['/myprograms']);
                }
                else{
                  alert("Program creation failed!");
                }
              },
              (error: HttpErrorResponse) => {
                alert(error.message);
              }
            );
          }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }else{
      if(this.program.id !== null) {console.log(this.program);
      this.myprogramService.updateProgram(this.program.id,this.program).subscribe(
        (response: Program) => {
          this.program = response; console.log(this.selectedFiles.length);
          if(this.selectedFiles.length !== 0 && this.program.id !== null) {
          this.myprogramService.uploadImagesForProgram(dataForm,this.program.id).subscribe(
            (response: HttpResponse<any>) => {console.log("RADI");
              if(response.status === 200) {
                this.router.navigate(['/myprograms']);
              }
              else{
                alert("Program creation failed!");
              }
            },
            (error: HttpErrorResponse) => {
              alert(error.message);
            }
          );
        }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        });
        this.router.navigate(['/myprograms']);
      }
    }
  }

  onFileSelected(event: any) {
    const files: FileList = event.target.files;
    const fileArray = Array.from(files);

    this.selectedFiles = this.selectedFiles.concat(fileArray);

     fileArray.forEach( file => {
        const reader = new FileReader();
  
        reader.onload = (e: any) => {
          this.previews.push(e.target.result);
        };
        
        reader.readAsDataURL(file);
      });
    }


    removePreview(preview: string) {
      const index = this.previews.indexOf(preview);
      const previewName = preview.replace("http://localhost:8080", "");
      const indexPic = this.program.pictures?.indexOf(previewName);
      console.log(previewName);

    if (index !== -1) {
      this.previews.splice(index, 1);

      if (indexPic !== undefined && indexPic !== -1)  {
        this.program.pictures?.splice(indexPic, 1);
        return;
      }

        const filesArray = Array.from(this.selectedFiles);
  
        filesArray.splice(index, 1); 
       
        const dataTransfer = new DataTransfer();
      filesArray.forEach((file: any) => {
        dataTransfer.items.add(file);
      });

      this.selectedFiles = Array.from(dataTransfer.files);

      }
    }

      private fillDataForm(dataForm: FormData, pictures: File[]){
        pictures.forEach(file => {
        dataForm.append('pictures', file, file.name);
       })
      }

      compareById(idFirst: any, idSecond: any) : boolean {
        return idFirst && idSecond && idFirst.id == idSecond.id;
     }
}
