import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisementServiceComponent } from './advertisement-service.component';

describe('AdvertisementServiceComponent', () => {
  let component: AdvertisementServiceComponent;
  let fixture: ComponentFixture<AdvertisementServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertisementServiceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdvertisementServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
