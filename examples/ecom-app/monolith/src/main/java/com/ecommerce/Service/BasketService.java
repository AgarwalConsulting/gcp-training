package com.ecommerce.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Dto.BasketDto;
import com.ecommerce.Entity.Basket;
import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Stock;
import com.ecommerce.Repository.BasketInsertRepository;
import com.ecommerce.Repository.BasketRepository;
import com.ecommerce.Repository.StockRepository;
import com.ecommerce.Repository.UserRepository;

@Service
@Transactional
public class BasketService {
	
	@Autowired UserRepository userRepository;
	@Autowired BasketRepository basketRepository;
	@Autowired BasketInsertRepository basketInsertRepository;
	@Autowired ProductService productService;
	@Autowired StockRepository stockRepository;
	
	public BasketDto getComprehensiveBasketByPrincipal(Principal principal) {
		
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		List<Basket> basketItems = basketRepository.findByUserId(userId);
		
		List<Product> products = basketItems.stream()
							     .map(Basket::getProductId)
							     .map(pid -> productService.getProductById(pid))
							     .collect(Collectors.toList());
		
		
		List<Integer> basketQtys = new ArrayList<Integer>();
		List<Integer> maxQtys = new ArrayList<Integer>();
		List<String> sizeCodes = new ArrayList<String>();
		List<Long> sizeIds = new ArrayList<Long>();
		
		// Necessary data to display cart page
		for(Basket basketItem : basketItems) {
			// From the Basket objects, we only need the quantity attribute
			basketQtys.add(basketItem.getQuantity());
			// From the Stock objects, we need currentStock, sizeId and sizeCode attributes
			// Stock object could have also be passed as a whole
			// Yet it is more convenient on the front-end this way
			Stock stockObj = stockRepository.findByProductIdAndSizeId(basketItem.getProductId(), basketItem.getSizeId());
			maxQtys.add(stockObj.getCurrentStock());
			sizeCodes.add(stockObj.getSizeCode());
			sizeIds.add(stockObj.getSizeId());
		}

		return new BasketDto(products, basketQtys, maxQtys, sizeCodes, sizeIds);

	}
	
	public List<Product> getCompactBasketByPrincipal(Principal principal) {
		
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		List<Basket> basketItems = basketRepository.findByUserIdGroupByProductId(userId);
		
		List<Product> basketProducts = new ArrayList<Product>();
		for(int i=0; i<basketItems.size(); i++) {
			Product product = productService.getProductById(basketItems.get(i).getProductId());
			product.setQuantity(basketItems.get(i).getQuantity());
			basketProducts.add(product);
		}
		
		return basketProducts;
	}
	
	public Integer getBasketTotalByPrincipal(Principal principal) {
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		return basketRepository.findBasketTotalByUserId(userId);
	}
	
	public void deleteBasketRow(Principal principal, Long sizeId, Long productId) {
		
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		basketRepository.deleteByUserIdAndSizeIdAndProductId(userId, sizeId, productId);
		
		
	}

	// Get basket properties ready -> Initialize basket object -> Persist with entity manager	
	public void addProductToBasket(Principal principal, String sizeSelect, Long productId, Integer quantity) {
		
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		
		// To enable javascript operations, value of the sizeSelect looks like: 
		// {currentStock}///{sizeCode} so we only need the second part
		Long sizeId = Long.valueOf(sizeSelect.split("///")[1]);
		
		Basket basket = new Basket(userId, productId, sizeId, quantity);
		basketInsertRepository.insert(basket);
		
	}
	
	public boolean isBasketEmpty(Principal principal) {
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		return (basketRepository.findBasketTotalByUserId(userId) == null);
	}

	public void updateBasket(Principal principal, String[] quantities) {
		Long userId = userRepository.findByEmail(principal.getName()).getUserId();
		List<Basket> basketRows = basketRepository.findByUserId(userId);
		
		for(int i=0; i<basketRows.size(); i++) {
			Basket basket = basketRows.get(i);
			Integer updatedQuantity = Integer.parseInt(quantities[i]);
			basket.setQuantity(updatedQuantity);
			basketRepository.save(basket);
		}
		
	}

	
	
}
