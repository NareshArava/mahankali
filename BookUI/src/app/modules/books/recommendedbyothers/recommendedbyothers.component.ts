import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { RecommendedbyothersService } from '../recommendedbyothers.service';
import { MatSnackBar } from '@angular/material';
import { book } from '../book';


@Component({
  selector: 'app-recommendedbyothers',
  templateUrl: './recommendedbyothers.component.html',
  styleUrls: ['./recommendedbyothers.component.css']
})
export class RecommendedbyothersComponent implements OnInit {

  errorDiv: boolean = false;
  errorMessage: string;
  books: Array<book>;
  afterSearch: boolean;
  bookList: any = [];

  constructor(private snackbar: MatSnackBar, private recommendedbyothersservice: RecommendedbyothersService, private authSerice: AuthenticationService, private authService: AuthenticationService, private viewService: RecommendedbyothersService) { }

  ngOnInit() {
    this.bookList = new Array<any>();
    console.log(this.authSerice.user);
    this.recommendedbyothersservice.getRecommendations(this.authSerice.user).subscribe(
      data => {
        console.log(this.authSerice.user);
        console.log(data);
        if (data.length > 0) {
          this.bookList = data;
        }
      }
    );
  }
}
