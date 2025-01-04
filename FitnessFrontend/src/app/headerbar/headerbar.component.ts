import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category.model';
import { Program } from '../model/program.model';
import { HeaderbarService } from './headerbar.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-headerbar',
  templateUrl: './headerbar.component.html',
  styleUrl: './headerbar.component.css'
})
export class HeaderbarComponent implements OnInit{

  categories : Category[] = [];
  programs: Program[] = [];
  searchText: any;
  limit: number = 5;
  avatar: any;

  constructor(private headerbarService: HeaderbarService, private router: Router, 
    public authService: AuthService) {}

  ngOnInit(): void {
    const temp = this.authService.isLoggedIn();
    if(temp)
      this.getAvatar();
  }

  getAvatar(){
    this.headerbarService.getAvatarById(this.authService.getUserId()).subscribe(
      (response: string) =>{
        this.avatar = response;
      },
      (error: HttpErrorResponse) => {
        if(error.status === 204)
          return;
        else
          alert("Error");
      }
    )
  }

  onSearch(event: any) {
    const searchTerm = event.target.value.trim();

    if (searchTerm.length >= 3) {
      this.searchForCategories(searchTerm);
      this.searchForPrograms(searchTerm);
    }
  }

  searchForCategories(searchTerm: any){
    this.headerbarService.getCategoriesByName(searchTerm, this.limit).subscribe(
      (response: Category[]) => {
        this.categories = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  searchForPrograms(searchTerm: any){
    this.headerbarService.getProgramsByName(searchTerm, this.limit).subscribe(
      (response: Program[]) => {
        this.programs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
