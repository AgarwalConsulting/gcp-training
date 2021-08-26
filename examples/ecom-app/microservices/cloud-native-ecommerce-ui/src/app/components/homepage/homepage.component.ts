import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ProductDto} from "../../models/ProductDto";
import {ProductInfoService} from "../../services/product-info.service";
import {StockService} from "../../services/stock/stock.service";
import {ImageService} from "../../services/image/image.service";

@Component({
  selector: 'homepage-component',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  @ViewChild('wrapper') wrapper: ElementRef;
  recentBlazers: ProductDto[];
  recentSuits: ProductDto[];
  recentTrousers: ProductDto[];
  recentShirts: ProductDto[];

  constructor(private renderer: Renderer2,
              private productInfoService: ProductInfoService,
              private imageService: ImageService) { }

  ngOnInit(): void {

    this.productInfoService.getRecentProductsByCategoryName('Trousers')
        .subscribe(recentTrousers => {
          this.recentTrousers = recentTrousers;
          recentTrousers.forEach(trouser => {
            this.imageService.getAllImagesByProductId(trouser.productId)
                .subscribe(images => trouser.imageDtoList = images);
          })
        });

    this.productInfoService.getRecentProductsByCategoryName('Shirts')
        .subscribe(recentShirts => {
          this.recentShirts = recentShirts;
          recentShirts.forEach(shirt => {
            this.imageService.getAllImagesByProductId(shirt.productId)
                .subscribe(images => shirt.imageDtoList = images);
          })
        });

    this.productInfoService.getRecentProductsByCategoryName('Blazers')
        .subscribe(recentBlazers => {
          this.recentBlazers = recentBlazers;
          recentBlazers.forEach(blazer => {
            this.imageService.getAllImagesByProductId(blazer.productId)
                .subscribe(images => blazer.imageDtoList = images);
          })
        });

    this.productInfoService.getRecentProductsByCategoryName('Suits')
        .subscribe(recentSuits => {
          this.recentSuits = recentSuits;
          recentSuits.forEach(suit => {
            this.imageService.getAllImagesByProductId(suit.productId)
                .subscribe(images => suit.imageDtoList = images);
          })
        });

  }

  onToggleOverlay(actionName): void {
    this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

}
