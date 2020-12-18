import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RecommendedbyothersComponent } from './recommendedbyothers.component';

describe('RecommendedbyothersComponent', () => {
  let component: RecommendedbyothersComponent;
  let fixture: ComponentFixture<RecommendedbyothersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RecommendedbyothersComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedbyothersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
