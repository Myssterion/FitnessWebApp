import { TestBed } from '@angular/core/testing';

import { CustomersupportService } from './customersupport.service';

describe('CustomersupportService', () => {
  let service: CustomersupportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomersupportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
