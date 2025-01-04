import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import Parser  from 'rss-parser';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private rssFeedUrl = '/feed/AceFitFacts';
  private exerciseUrl = '/api/v1/exercises?muscle=biceps';
  private apiKey = 'bTy/2ZvAZ921LbCUrEx5lg==I4z5RB0iTdDIw0vP';
  private cachedRssFeed: any[] | null = null;
  private cachedExercises:  Observable<any[]> | null = null;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  async getRSSFeed(): Promise<any[]> {
    if (this.cachedRssFeed) {console.log("RSS CACHED");
      return this.cachedRssFeed;
    }

    const xmlData = await this.http.get(this.rssFeedUrl, { responseType: 'text' }).toPromise();
    if (!xmlData) {
      throw new Error('Failed to fetch XML data for RSS feed.');
    }
    const parser = new Parser();
    const parsedData = await parser.parseString(xmlData);
    this.cachedRssFeed = parsedData.items;
    return this.cachedRssFeed;
  }

  getExerciseSuggestions() :  Observable<any[]> {
    if(this.cachedExercises){console.log("EXERCISED CACHED");
      return this.cachedExercises;
    }
    const headers = new HttpHeaders()
    .set('X-Api-Key', this.apiKey)
    .set('Content-Type', 'application/json');
    const url = `${this.exerciseUrl}`;
    
    this.cachedExercises = this.http.get(url, { headers, responseType: 'json' })
      .pipe(
        map(response => {
          if (Array.isArray(response)) {
            return response;
          } else {
            throw new Error('Response is not an array');
          }
        }),
        catchError(error => {
          console.error('Error fetching exercises:', error);
          return throwError(error);
        })
      );

    return this.cachedExercises;
  }
}
