import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiRegisterPageComponent } from './api-register-page.component';

describe('ApiRegisterPageComponent', () => {
  let component: ApiRegisterPageComponent;
  let fixture: ComponentFixture<ApiRegisterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApiRegisterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiRegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
