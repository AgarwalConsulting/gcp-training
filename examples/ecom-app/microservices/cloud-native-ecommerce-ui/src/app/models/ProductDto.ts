import {ImageDto} from "./ImageDto";
import {StockDto} from "./StockDto";

export class ProductDto {
    productId: number;
    productName: string;
    currentPrice: number;
    imageDtoList: ImageDto[];
    stockDtoList: StockDto[];
}
