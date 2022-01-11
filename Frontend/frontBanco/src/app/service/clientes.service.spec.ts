import { TestBed } from '@angular/core/testing';

import { ClientesService } from './clientes.service';

describe('ServiceComponent', () => {
  let service: ClientesService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientesService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
