import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiLoginPageComponent } from './api-login-page.component';

describe('ApiLoginPageComponent', () => {
  let component: ApiLoginPageComponent;
  let fixture: ComponentFixture<ApiLoginPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApiLoginPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiLoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
