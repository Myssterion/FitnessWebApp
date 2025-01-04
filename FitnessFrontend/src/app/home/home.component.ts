import { Component, OnInit } from '@angular/core';
import { HomeService } from './home.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  rssFeed: any[] = [];
  exerciseSuggestions: any[] = [];

  constructor(private homeService: HomeService, private route: ActivatedRoute, public authService: AuthService) {}

  ngOnInit(): void {
   this.loadRSSFeed();
   this.loadExerciseSuggestions();
  }

  async loadRSSFeed() {
    this.rssFeed = await this.homeService.getRSSFeed();
  }

  loadExerciseSuggestions() {
    this.homeService.getExerciseSuggestions().subscribe((data: any[]) => {
      this.exerciseSuggestions = data;
    });
  }
}
