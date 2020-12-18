import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { book } from './book';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable()
export class RecommendationService {
  saveUrl: string = "http://localhost:9899/api/recommendationbookservice/book";
  getUrl: string = "http://localhost:9899/api/recommendationbookservice/books/";
  delUrl: string = "http://localhost:9899/api/recommendationbookservice/book/";

  constructor(private http: HttpClient) { }
  getBooks(apiUrl): Observable<any> {
    return this.http.get<any>(apiUrl);
  }


  saveBook(saveBook) {
    console.log(saveBook);
    return this.http.post(this.saveUrl, saveBook);
  }


  getRecommendations(userId): Observable<any> {
    return this.http.get<any>(this.getUrl);
  }

  getMyRecommendations(): Observable<Array<book>> {
    return this.http.get<Array<book>>("http://localhost:9899/api/recommendationbookservice/books");
  }

  deleteFromRecommendationList(book: book) {
    console.log(book);

    return this.http.delete(this.delUrl + book.id);
  }

}
