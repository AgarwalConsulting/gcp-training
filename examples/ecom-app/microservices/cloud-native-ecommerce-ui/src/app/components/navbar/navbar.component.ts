import {Component, ElementRef, EventEmitter, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import {BasketService} from "../../services/basket/basket.service";
import {BasketSRowDto} from "../../models/BasketSRowDto";
import {ProductInfoService} from "../../services/product-info.service";
import {ImageService} from "../../services/image/image.service";

@Component({
  selector: 'navbar-component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Output() overlayToggle = new EventEmitter();
  @ViewChild('sidebarCart') sidebarCart: ElementRef;
  basketSummaryRows: BasketSRowDto[];
  isLoggedIn: boolean;
  isBasketEmpty: boolean;
  basketTotal: number;

  constructor(private renderer: Renderer2,
              private basketService: BasketService,
              private productInfoService: ProductInfoService,
              private imageService: ImageService) { }

  ngOnInit(): void {

    // TODO: Authentication
    this.isLoggedIn = true;
    this.basketTotal = 0;

    // TODO: parametrize username
    this.basketService.getBasketSummaryRows("dummy-name")
        .subscribe(basketSummaryRows => {
          this.isBasketEmpty = basketSummaryRows.length === 0;
          this.basketSummaryRows = basketSummaryRows;
          basketSummaryRows.forEach(row => {
            this.imageService.getImageByProductId(row.productId)
                .subscribe(imageDto => row.imageUrl = imageDto.imageUrl);
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

  onTriggerSearch(mainSearchActive: HTMLDivElement) {
    mainSearchActive.classList.add('inside');
  }

  onCloseSearch(mainSearchActive: HTMLDivElement) {
    mainSearchActive.classList.remove('inside');
  }

  onTriggerSidebar() {
    this.renderer['addClass'](this.sidebarCart.nativeElement, 'inside');
    this.overlayToggle.emit('addClass');
  }

  onCloseSidebar() {
    this.renderer['removeClass'](this.sidebarCart.nativeElement, 'inside');
    this.overlayToggle.emit('removeClass');
  }
}
