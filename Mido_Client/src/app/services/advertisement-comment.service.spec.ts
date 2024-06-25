import { TestBed } from '@angular/core/testing';

import { AdvertisementCommentService } from './advertisement-comment.service';

describe('AdvertisementCommentService', () => {
  let service: AdvertisementCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdvertisementCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
