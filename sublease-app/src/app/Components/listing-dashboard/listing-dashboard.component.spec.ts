import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListingDashboardComponent } from './listing-dashboard.component';

describe('ListingDashboardComponent', () => {
  let component: ListingDashboardComponent;
  let fixture: ComponentFixture<ListingDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListingDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListingDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
