import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetShelterComponent } from './pet-shelter.component';

describe('PetShelterComponent', () => {
  let component: PetShelterComponent;
  let fixture: ComponentFixture<PetShelterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetShelterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
