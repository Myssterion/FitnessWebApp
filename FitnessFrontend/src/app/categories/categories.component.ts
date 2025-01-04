import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { CategoryService } from '../service/category.service';
import { AuthService } from '../service/auth.service';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent implements OnInit{

  categories: Category[] = [];

  userSubscriptions: Category[] = [];
  constructor(private categoryService: CategoryService, private authService: AuthService) {}

  ngOnInit(): void {
   this.getCategories();
   this.getUserSubscriptions();
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(
      (response: Category[]) => {
        this.categories = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getUserSubscriptions() {
    this.categoryService.getUserSubscriptions(this.authService.getUserId()).subscribe(
      (response: Category[]) => {
        if(response !== null)
          this.userSubscriptions = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  isSubscribed(category: Category) : boolean {
    return this.userSubscriptions.some(subscribedCategory => subscribedCategory.id === category.id);
  }

  toggleSubscription(category: Category) {
    if (this.isSubscribed(category)) {
      this.userSubscriptions = this.userSubscriptions.filter(subscribedCategory => subscribedCategory.id !== category.id);
      if(category.id !== null){
        if (!this.unsubscribe(category.id)) {
          // Roll back the UI change if the unsubscribe operation fails
          this.userSubscriptions.push(category);
          console.error('Unsubscription failed');
        }
      }
    } else {
      this.userSubscriptions.push(category);
      if(category.id !== null) {
        if (!this.subscribe(category.id)) {
          // Roll back the UI change if the subscribe operation fails
          this.userSubscriptions = this.userSubscriptions.filter(subscribedCategory => subscribedCategory.id !== category.id);
          console.error('Subscription failed');
        }
      }
    }
  }

  subscribe(categoryId: number): Observable<boolean> {
    const result = new Subject<boolean>();
    this.categoryService.subscribeUser(this.authService.getUserId(), categoryId).subscribe(
      (response: HttpResponse<any>) => {
        if (response && response.status === 200) {
          result.next(true);
        } else {
          result.next(false);
        }
        result.complete();
      },
      (error: HttpErrorResponse) => {
        result.next(false);
        result.complete();
      }
    );

    return result.asObservable();
  }

  unsubscribe(categoryId: number): Observable<boolean> {
    const result = new Subject<boolean>();

    this.categoryService.unsubscribeUser(this.authService.getUserId(), categoryId).subscribe(
      (response: HttpResponse<any>) => {
        if (response && response.status === 200) {
          result.next(true);
        } else {
          result.next(false);
        }
        result.complete();
      },
      (error:  HttpErrorResponse) => {
        result.next(false);
        result.complete();
      }
    );

    return result.asObservable();
  }
}
