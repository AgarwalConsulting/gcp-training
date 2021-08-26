import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {BasketService} from "../../services/basket/basket.service";
import {ProductInfoService} from "../../services/product-info.service";
import {BasketDRowDto} from "../../models/BasketDRowDto";
import {ImageService} from "../../services/image/image.service";
import {StockService} from "../../services/stock/stock.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @ViewChild('wrapper') wrapper: ElementRef;
  basketDetailedRows: BasketDRowDto[];
  basketTotal: number;

  constructor(private renderer: Renderer2, private router: Router,
              private basketService: BasketService,
              private productInfoService: ProductInfoService,
              private imageService: ImageService,
              private stockService: StockService) { }

  ngOnInit(): void {

      this.basketTotal = 0;

      //TODO: parametrize username
      this.basketService.getBasketDetailedRows("dummy-name")
          .subscribe(basketDetailedRows => {
              this.basketDetailedRows = basketDetailedRows;
              basketDetailedRows.forEach(row => {
                  this.productInfoService.getProductNameByProductId(row.productId)
                      .subscribe(productName => row.productName = productName);
                  this.imageService.getImageByProductId(row.productId)
                      .subscribe(image => row.imageUrl = image.imageUrl);
                  this.stockService.getSizeCodeBySizeId(row.sizeId)
                      .subscribe(sizeCode => row.sizeCode = sizeCode);
                  this.productInfoService.getCurrentPriceByProductId(row.productId)
                      .subscribe(currentPrice => {
                          row.currentPrice = currentPrice;
                          row.total = row.quantity * currentPrice;
                          this.basketTotal += row.total;
                      })
              })
          })
  }

  recomputeTotals(select: HTMLSelectElement) {
      const productId: number = +select.id.split("/")[0];
      const sizeId: number = +select.id.split("/")[1];
      const newQuantity: number = +select.value;

      const changed = this.basketDetailedRows.find(row => row.productId == productId && row.sizeId == sizeId);
      changed.quantity = newQuantity;
      changed.total = newQuantity * changed.currentPrice;

      this.basketTotal = 0;
      this.basketDetailedRows.forEach(row => {
          this.basketTotal += row.quantity * row.currentPrice;
      })
  }

  onToggleOverlay(actionName): void {
      this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

  deleteRow(row: BasketDRowDto): void {
      this.basketService.deleteFromBasket(row.productId, row.sizeId)
          .subscribe(response => location.reload());
  }

  updateBasket(): void {
    // TODO: FormArray implementation
  }

  proceedToCheckout(): void {
      this.updateBasket();
  }


}
