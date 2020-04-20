import { Injectable } from '@angular/core';
import { ListingRequest } from 'src/app/Models/ListingRequest/ListingRequest';

@Injectable({
  providedIn: 'root'
})
export class ListingService {

  private listings:ListingRequest[]=[
    new ListingRequest('BroadStone Arden','Address','https://payload.cargocollective.com/1/1/46427/13993390/Broadstone-arden-park-paseo-carly-ealey-jaguar-restrooms_1000.JPG'),
    new ListingRequest("The Alton", "Address",'https://images1.apartments.com/i2/hexGOgGi93WXqaBkorfpoNDUzpx7bcMNhzcvIhbmbKk/117/the-alton-irvine-ca-building-photo.jpg'),
    new ListingRequest("The Kelvin", "Address",'https://images1.apartments.com/i2/FHopGjiO2w4bXnuU_xPX4vXfr_qQHFSMNKDo-44DIY4/117/the-kelvin-irvine-ca-the-kelvin-apartments-building.jpg'),
    new ListingRequest('CenterPointe', 'Address', 'https://static.trulia-cdn.com/pictures/thumbs_5/ps.115/3/c/f/6/picture-uh=eca823a98ef505327583f68940b728-ps=3cf63f83b5aff46d98608dace976ad94.jpg')
  ];
  constructor() { }

  getListings(){
    return this.listings.slice();
  }
}
// private recipes:Recipe[] = [
//   new Recipe('Shakshuka', 'Shakshuka is an Israeli and Middle Eastern meal of poached eggs in a simmering tomato sauce', 
// 'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2018/12/Shakshuka-19.jpg')
// ];

// getRecipes(){
// return this.recipes.slice();
// }