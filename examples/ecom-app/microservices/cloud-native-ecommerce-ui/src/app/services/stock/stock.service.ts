import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StockDto} from "../../models/StockDto";

@Injectable({
  providedIn: 'root'
})
export class StockService {

  BASE_URL: string = "http://localhost:8083";

  constructor(private http: HttpClient) { }

  getStockListByProductId(productId: number) {
    return this.http.get<StockDto[]>(this.BASE_URL + "/stock-list/" + productId);
  }

  getSizeCodeBySizeId(sizeId: number) {
    return this.http.get(this.BASE_URL + "/size-code/" + sizeId, {responseType: 'text'});
  }

}
