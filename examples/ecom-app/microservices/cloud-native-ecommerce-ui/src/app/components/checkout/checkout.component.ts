import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {BasketService} from "../../services/basket/basket.service";
import {FormBuilder} from "@angular/forms";
import {BasketSRowDto} from "../../models/BasketSRowDto";
import {ProductInfoService} from "../../services/product-info.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  @ViewChild('wrapper') wrapper: ElementRef;
  basketSummaryRows: BasketSRowDto[];
  basketTotal: number;
  // currentPrices response: [500,520]

  constructor(private renderer: Renderer2,
              private fb: FormBuilder,
              private basketService: BasketService,
              private productInfoService: ProductInfoService) { }

  ngOnInit(): void {

    this.basketTotal = 0;

    // TODO: parametrize username
    this.basketService.getBasketSummaryRows("dummy-name")
        .subscribe(basketSummaryRows => {
          this.basketSummaryRows = basketSummaryRows;
          basketSummaryRows.forEach(row => {
            this.productInfoService.getProductNameByProductId(row.productId)
                .subscribe(productName => row.productName = productName);
            this.productInfoService.getCurrentPriceByProductId(row.productId)
                .subscribe(currentPrice => {
                  row.currentPrice = currentPrice;
                  this.basketTotal += currentPrice * row.quantity;
                });
          })
        });

  }

  onToggleOverlay(actionName): void {
      this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

  onSubmit() {
      console.log("submit")
  }

  checkoutForm = this.fb.group({
    firstName: [''],
    lastName: [''],
    address: [''],
    email: [''],
    phoneNumber: [''],
    ccn: [''],
    owner: [''],
    month: [''],
    year: [''],
    cvc: ['']
  })

}
