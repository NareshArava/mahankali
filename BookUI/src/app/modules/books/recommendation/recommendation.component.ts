import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { RecommendationService } from '../recommendation.service';
import { MatSnackBar } from '@angular/material';
import { book } from '../book';
@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

  errorDiv: boolean = false;
  errorMessage: string;
  books: Array<book>;
  afterSearch: boolean;
  bookList: any = [];

  constructor(private snackbar: MatSnackBar, private recommendationservice: RecommendationService, private authSerice: AuthenticationService, private authService: AuthenticationService, private viewService: RecommendationService) { }

  ngOnInit() {
    this.bookList = new Array<any>();
    console.log(this.authSerice.user);
    this.recommendationservice.getRecommendations(this.authSerice.user).subscribe(
      data => {
        console.log(this.authSerice.user);
        console.log(data);
        if (data.length > 0) {
          this.bookList = data;
        }
      }
    );
  }

  Remove(book) {
    console.log(book);
    let message = `${book.title} deleted`;
    this.snackbar.open(message, '', {
      duration: 1000
    });
    this.recommendationservice.deleteFromRecommendationList(book).subscribe((book) => {
      console.log("deleted");
    });
    const index = this.bookList.indexOf(book);
    this.bookList.splice(index, 1);
  }
}

