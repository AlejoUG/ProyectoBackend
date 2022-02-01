import { TestBed } from '@angular/core/testing';

import { ProductoService } from './producto.service';

describe('ServiceComponent', () => {
  let service: ProductoService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductoService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});