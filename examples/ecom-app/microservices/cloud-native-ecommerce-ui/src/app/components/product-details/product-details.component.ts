import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {ProductInfoService} from "../../services/product-info.service";
import {ProductDto} from "../../models/ProductDto";
import {ActivatedRoute} from "@angular/router";
import {ImageService} from "../../services/image/image.service";
import {StockService} from "../../services/stock/stock.service";
import {BasketService} from "../../services/basket/basket.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  productId: number;
  @ViewChild('wrapper') wrapper: ElementRef;
  product: ProductDto;
  relatedProducts: ProductDto[];
  isOutOfStock: boolean;
  isLoggedIn: boolean;
  selectedSizeId: number;
  quantities: number[];

  constructor(private renderer: Renderer2, private route: ActivatedRoute,
              private productInfoService: ProductInfoService, private basketService: BasketService,
              private imageService: ImageService, private stockService: StockService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.productId = params['productId'];
      this.productInfoService.getProductDetailsByProductId(this.productId)
          .subscribe(product => {
            this.product = product;
            this.imageService.getAllImagesByProductId(product.productId)
                .subscribe(images => product.imageDtoList = images);
            this.stockService.getStockListByProductId(product.productId)
                .subscribe(stockList => {
                  product.stockDtoList = stockList;
                  this.selectedSizeId = this.product.stockDtoList[0].sizeId;
                  this.quantities = Array.from({length: this.product.stockDtoList[0].stock}, (_, i) => i + 1);
                });
          });
      this.productInfoService.getRelatedProductsByProductId(this.productId)
          .subscribe(relatedProducts => {
            this.relatedProducts = relatedProducts;
            relatedProducts.forEach(rp => {
              this.imageService.getAllImagesByProductId(rp.productId)
                  .subscribe(images => rp.imageDtoList = images);
            })
          });
    });

    // TODO: parametrize
    this.isOutOfStock = false;
    this.isLoggedIn = true;
  }

  onSubmit(select: HTMLSelectElement) {
    const productId: number = this.productId;
    const quantity: number = +select.value;
    const sizeId: number = this.selectedSizeId;
    this.basketService.addProductToBasket(productId, sizeId, quantity)
        .subscribe(basket => location.reload());
  }

  onToggleOverlay(actionName): void {
    this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

  sizeOnChange(sizeSelect: HTMLSelectElement) {
    const maxStock: number = +sizeSelect.value.split('/')[0];
    this.quantities = Array.from({length: maxStock}, (_, i) => i + 1);
    this.selectedSizeId = +sizeSelect.value.split('/')[1];
  }

}
