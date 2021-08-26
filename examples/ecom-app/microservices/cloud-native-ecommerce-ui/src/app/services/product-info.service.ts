import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ProductDto } from "../models/ProductDto";

@Injectable({
  providedIn: 'root'
})
export class ProductInfoService {

  BASE_URL: string = "http://localhost:8081";

  constructor(private http: HttpClient) { }

  getProductDetailsByProductId(productId: number): Observable<ProductDto> {
    return this.http.get<ProductDto>(this.BASE_URL + "/product/" + productId);
  }

  getRelatedProductsByProductId(productId: number): Observable<ProductDto[]> {
    return this.http.get<ProductDto[]>(this.BASE_URL + "/related-products/" + productId);
  }

  getRecentProductsByCategoryName(categoryName: string): Observable<ProductDto[]> {
    return this.http.get<ProductDto[]>(this.BASE_URL + "/recent-products/" + categoryName);
  }

  getProductNameByProductId(productId: number): Observable<string> {
    return this.http.get(this.BASE_URL + "/product-name/" + productId, {responseType: 'text'});
  }

  getCurrentPriceByProductId(productId: number): Observable<number> {
    return this.http.get<number>(this.BASE_URL + "/current-price/" + productId);
  }

}
