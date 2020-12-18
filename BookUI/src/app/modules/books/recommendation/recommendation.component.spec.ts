import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RecommendationComponent } from './recommendation.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { RecommendationService } from '../recommendation.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { MatSelectModule, MatCardModule, MatButtonModule, MatSnackBarModule, MatDialogModule, MatInputModule, MatFormFieldModule, MatIconModule } from '@angular/material';


describe('RecommendationComponent', () => {
  let component: RecommendationComponent;
  let fixture: ComponentFixture<RecommendationComponent>;
  let viewservice: RecommendationService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RecommendationComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatSelectModule,
        FormsModule,
        MatCardModule,
        MatButtonModule, MatSnackBarModule, MatDialogModule,
        MatDialogModule, MatInputModule, FormsModule,
        MatFormFieldModule, MatIconModule
      ],
      providers: [
        RecommendationService,
        AuthenticationService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('users recommendation List ', () => {
    viewservice = fixture.debugElement.injector.get(RecommendationService);
    const recommendation: any = JSON.parse(JSON.stringify({
      book:
        {
          title: 'someone',
          author: 'someone',
          isbn: '3453',
          key: '435354'
        },
    }));
    spyOn(viewservice, "getRecommendations").and.returnValue(of(recommendation));
    component.ngOnInit();
    expect(viewservice.getRecommendations).toHaveBeenCalled();
  });

  it('when user has no Recommendations ', () => {
    viewservice = fixture.debugElement.injector.get(RecommendationService);
    const recommendation: any = JSON.parse(JSON.stringify({
      book: [
      ]
    }));
    spyOn(viewservice, "getRecommendations").and.returnValue(of(recommendation));
    component.ngOnInit();
    expect(viewservice.getRecommendations).toHaveBeenCalled();
  });

});
