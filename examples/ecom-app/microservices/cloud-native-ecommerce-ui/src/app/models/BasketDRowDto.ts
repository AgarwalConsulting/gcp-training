export class BasketDRowDto {

    constructor(productId: number, sizeId: number, quantity: number) {
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    imageUrl: string;
    productId: number;
    productName: string;
    sizeId: number;
    sizeCode: string;
    quantity: number;
    currentPrice: number;
    total: number;
}
