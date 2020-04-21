import { Component, OnInit } from '@angular/core';
import { ListingRequest } from 'src/app/Models/ListingRequest/ListingRequest';
import { ListingService } from 'src/app/Services/Listing/listing.service';

@Component({
  selector: 'app-listing-dashboard',
  templateUrl: './listing-dashboard.component.html',
  styleUrls: ['./listing-dashboard.component.css']
})
export class ListingDashboardComponent implements OnInit {
  listings:ListingRequest[];

  constructor(private ls:ListingService) { }

  ngOnInit(): void {
    this.listings = this.ls.getListings().slice(0,5)
  }


}
