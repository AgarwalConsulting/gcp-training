import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ImageDto} from "../../models/ImageDto";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  BASE_URL: string = "http://localhost:8082";

  constructor(private http: HttpClient) { }

  getImageByProductId(productId: number) {
    return this.http.get<ImageDto>(this.BASE_URL + "/image/" + productId);
  }

  getAllImagesByProductId(productId: number) {
    return this.http.get<ImageDto[]>(this.BASE_URL + "/image-list/" + productId);
  }

}
