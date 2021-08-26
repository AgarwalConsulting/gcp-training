package com.ecommerce.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Dto.BasketDto;
import com.ecommerce.Dto.BillingDto;
import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Sale;
import com.ecommerce.Entity.SalesDetail;
import com.ecommerce.Entity.Stock;
import com.ecommerce.Repository.BasketRepository;
import com.ecommerce.Repository.SaleInsertRepository;
import com.ecommerce.Repository.SalesDetailInsert;
import com.ecommerce.Repository.StockRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class CheckoutService {
	
	@Autowired BasketRepository basketRepository;
	@Autowired UserRepository userRepository;
	@Autowired StockRepository stockRepository;
	@Autowired SaleInsertRepository saleInsertRepository;
	@Autowired SalesDetailInsert salesDetailInsert;
	@Autowired PaymentService paymentService;
	@Autowired BasketService basketService;

	@Transactional
	public boolean checkout(Principal principal, BillingDto billingDto) {
		
		boolean isPaymentSuccessful = paymentService.tryPayment(billingDto);
		
		if(!isPaymentSuccessful) { return false; } else {
		
			// Update sale table
			Long userId = userRepository.findByEmail(principal.getName()).getUserId();
			Sale sale = new Sale(userId, new Date());
			Long saleId = saleInsertRepository.insert(sale);
			
			// DB: Insert into sales_detail & update stock & delete from basket
			BasketDto basketDto = basketService.getComprehensiveBasketByPrincipal(principal);
			List<Product> products = basketDto.getItems();
			List<Integer> saleQuantities = basketDto.getBasketQtys(); 
			List<Long> sizeIds = basketDto.getSizeIds();
			
			for(int i=0; i<products.size(); i++) {
				
				Product p = products.get(i);
				Long productId = p.getProductId();
				Integer currentPrice = p.getCurrentPrice();
				Long sizeId = sizeIds.get(i);
				Integer saleQty = saleQuantities.get(i);
				
				SalesDetail sd = new SalesDetail(saleId, productId, saleQty, sizeId, currentPrice);
				salesDetailInsert.insert(sd);
				
				Stock stock = stockRepository.findByProductIdAndSizeId(productId, sizeId);
				stock.setCurrentStock( stock.getCurrentStock() - saleQty );
				stockRepository.save(stock);
				
				basketRepository.deleteByUserIdAndSizeIdAndProductId(userId, sizeId, productId);
				
			}
			
			return true;
		
		} // end of comprehensive else
	} // end of checkout method
	
}
