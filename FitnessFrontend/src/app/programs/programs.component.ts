import { Component, OnInit } from '@angular/core';
import { ProgramInfo } from '../model/programInfo.model';
import { ProgramsService } from './programs.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CategoryService } from '../service/category.service';
import { DifficultyService } from '../service/difficulty.service';
import { Category } from '../model/category.model';
import { Difficulty } from '../model/difficulty.model';
import { Filter } from '../model/filter.model';
import { Attribute } from '../model/attribute.model';
import { AttributeService } from '../service/attribute.service';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrl: './programs.component.css'
})
export class ProgramsComponent implements OnInit {
  public currentPage: number = 1;
  public pageSize: number = 6;
  public programs: ProgramInfo[] = [];
  public categories: Category[] = [];
  public difficulties: Difficulty[] = [];
  public attributes: Attribute[] = [];
  filters: Filter = new Filter();
  public totalItems = this.programs.length;
  public isSearch = false;

  categoryActive: boolean = false;
  attributeActive: boolean = false;
  difficultyActive: boolean = false;
  priceActive: boolean = false;
  durationActive: boolean = false;
 

  constructor(private programsService: ProgramsService,
     private route: ActivatedRoute, private categoryService: CategoryService,
     private difficultyService: DifficultyService, private attributeService: AttributeService,
     private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.resetFilterValues();
      this.loadFilterValues();
      if (Object.keys(params).length !== 0) { 
        this.setQueryParam(params, this.filters);
        this.getFilteredPrograms();
        this.isSearch = true;
      } else {
        this.getPrograms();
        this.isSearch = false;
      }
    });
  }

  setQueryParam(params: Params, filters: Filter) {
    if (params["categoryId"]) {
      this.categoryActive = true;
      filters.categoryId = params["categoryId"];
     
    } 

    if (params["attributeId"]) {
      this.attributeActive = true;
      filters.attributeId = params["attributeId"];
     
    } 

  if (params["difficultyId"]) {
    this.difficultyActive = true;
    filters.difficultyId = params["difficultyId"];
  } 

  if (params["durationFrom"]) {
    this.durationActive = true;
    filters.durationFrom = params["durationFrom"];
  } 

  if (params["durationTo"]) {
    this.durationActive = true;
    filters.durationTo = params["durationTo"];
  } 

  if (params["priceFrom"]) {
    this.priceActive = true;
    filters.priceFrom = params["priceFrom"];
  } 

  if (params["priceTo"]) {
    this.priceActive = true;
    filters.priceTo = params["priceTo"];
  }
  }

  private resetFilterValues() {
    this.categoryActive = false;
    this.attributeActive = false;
    this.difficultyActive = false;
    this.durationActive = false;
    this.priceActive = false;
  }

  private loadFilterValues(){
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

  public getPrograms() {
    this.programsService.getPrograms().subscribe(
      (response: ProgramInfo[]) => {
        this.programs = response;  
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getFilteredPrograms() {
    this.programsService.getFilteredPrograms(this.filters).subscribe(
      (response: ProgramInfo[]) => {
        this.programs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public applyCategoryFilter(category_id: number | null) {
    if(category_id !== null) {
      this.loadAndShowAttributes(category_id);
      this.attributeActive = false;
      this.categoryActive = true;
      this.filters.categoryId = category_id;
      this.applyFilters();
    }
  }

  public applyAttributeFilter(attribute_id: number | null) {
    if(attribute_id !== null) {
      this.attributeActive = true;
      this.filters.attributeId = attribute_id;
      this.applyFilters();
    }
  }


  loadAndShowAttributes(category_id: number) {
   this.attributeService.getAttributes(category_id).subscribe(
    (response: Attribute[]) => {
      this.attributes = response;
    }
   ),
   (error: HttpErrorResponse) => {
    alert(error.message);
   }
  }

  public applyDifficultyFilter(difficulty_id: number | null) {
    if(difficulty_id !== null) {
      this.difficultyActive = true;
      this.filters.difficultyId = difficulty_id;
      this.applyFilters();
    }
  }

  public applyFilters() {
    const queryParams: Params = {};

    if (this.categoryActive) {
      queryParams['categoryId'] = this.filters.categoryId;
    }
    if(this.categoryActive && this.attributeActive) {
      queryParams['attributeId'] = this.filters.attributeId;
    }
    if (this.difficultyActive) {
      queryParams['difficultyId'] = this.filters.difficultyId;
    }
    if(this.filters.durationFrom !== undefined) {
      queryParams['durationFrom'] = this.filters.durationFrom;
    }
    if(this.filters.durationTo !== undefined) {
      queryParams['durationTo'] = this.filters.durationTo;
    }
    if(this.filters.priceFrom !== undefined) {
      queryParams['priceFrom'] = this.filters.priceFrom;
    }
    if(this.filters.priceTo !== undefined) {
      queryParams['priceTo'] = this.filters.priceTo;
    }

    this.router.navigate(['/programs'], { queryParams: queryParams, relativeTo : this.route });
  }
}
