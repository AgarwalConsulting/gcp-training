import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BasketSRowDto} from "../../models/BasketSRowDto";
import {BasketDRowDto} from "../../models/BasketDRowDto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BasketService {

  BASE_URL: string = "http://localhost:8085";

  constructor(private http: HttpClient) { }

  // TODO: username or token? look up security best practices.
  getBasketSummaryRows(username: string): Observable<BasketSRowDto[]> {
    return this.http.get<BasketSRowDto[]>(this.BASE_URL + "/basket-summary/" + username);
  }

  getBasketDetailedRows(username: string): Observable<BasketDRowDto[]> {
    return this.http.get<BasketDRowDto[]>(this.BASE_URL + "/detailed-basket/" + username);
  }

  addProductToBasket(productId: number, sizeId: number, quantity: number): Observable<BasketDRowDto> {
    const basket: BasketDRowDto = new BasketDRowDto(productId, sizeId, quantity);
    return this.http.post<BasketDRowDto>(this.BASE_URL + "/add-to-basket", basket);
  }

  deleteFromBasket(productId: number, sizeId: number): Observable<any> {
    return this.http.delete(this.BASE_URL + "/delete-from-basket/" + productId + "/" + sizeId, { responseType: 'text' });
  }

}
