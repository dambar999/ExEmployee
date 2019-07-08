import { TestBed } from '@angular/core/testing';

import { ListticketserviceService } from './listticketservice.service';

describe('ListticketserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListticketserviceService = TestBed.get(ListticketserviceService);
    expect(service).toBeTruthy();
  });
});
