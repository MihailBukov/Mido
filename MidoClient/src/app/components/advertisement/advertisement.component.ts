import { Component } from '@angular/core';
import { AdvertisementDto } from '../../models/dtos/AdvertisementDto';

@Component({
  selector: 'app-advertisement',
  standalone: true,
  imports: [],
  templateUrl: './advertisement.component.html',
  styleUrl: './advertisement.component.css'
})
export class AdvertisementComponent {
  ad: AdvertisementDto;
}
