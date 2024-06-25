import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisementCommentComponent } from './advertisement-comment.component';

describe('AdvertisementCommentComponent', () => {
  let component: AdvertisementCommentComponent;
  let fixture: ComponentFixture<AdvertisementCommentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdvertisementCommentComponent]
    });
    fixture = TestBed.createComponent(AdvertisementCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
