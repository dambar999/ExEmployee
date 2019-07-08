import { TestBed } from '@angular/core/testing';

import { TicketmappingService } from './ticketmapping.service';

describe('TicketmappingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TicketmappingService = TestBed.get(TicketmappingService);
    expect(service).toBeTruthy();
  });
});
