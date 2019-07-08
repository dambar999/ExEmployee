import { TestBed } from '@angular/core/testing';

import { GetempService } from './getemp.service';

describe('GetempService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetempService = TestBed.get(GetempService);
    expect(service).toBeTruthy();
  });
});
