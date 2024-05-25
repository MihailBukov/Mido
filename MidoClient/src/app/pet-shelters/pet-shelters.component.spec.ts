import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetSheltersComponent } from './pet-shelters.component';

describe('PetSheltersComponent', () => {
  let component: PetSheltersComponent;
  let fixture: ComponentFixture<PetSheltersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetSheltersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetSheltersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
