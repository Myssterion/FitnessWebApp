import { TestBed } from '@angular/core/testing';

import { MyprogramService } from './myprogram.service';

describe('MyprogramService', () => {
  let service: MyprogramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyprogramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
