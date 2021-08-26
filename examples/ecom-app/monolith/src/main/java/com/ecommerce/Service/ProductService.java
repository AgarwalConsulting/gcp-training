package com.ecommerce.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Dto.FilterDto;
import com.ecommerce.Entity.Image;
import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Stock;
import com.ecommerce.Repository.CategoryRepository;
import com.ecommerce.Repository.ImageRepository;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Repository.StockRepository;

@Service
public class ProductService {

	@Autowired ProductRepository prodRepository;
	@Autowired CategoryRepository catRepository;
	@Autowired ImageRepository imgRepository;
	@Autowired StockRepository stockRepository;
	@Autowired EntityManager entityManager;
	
	public List<Product> getRecentProducts(String categoryName) {
		
		// The reason we first fetch the categoryid of the current product is that
		// Logic of the recent products is: Top 4 Products in the same category + closest price
		Long categoryId = catRepository.findByCategoryName(categoryName).get(0).getCategoryId();
		
		List<Product> recentProducts = prodRepository.findFirst4ByCategoryIdOrderByProductIdDesc(categoryId)
									   .stream().map(rp -> withImages(rp)).collect(Collectors.toList());
		
		return recentProducts;
				
	}
	

	public Product getProductById(Long productId) {
		
		Product product = prodRepository.findByProductId(productId);
		
		return withImagesAndStockInfo(product);
		
	}
	
	// Used in product details page to switch between Call to Action / Out of Stock buttons
	public boolean isOutOfStockByProductId(Long productId) {
		
		Integer totalStock = stockRepository.findTotalStockByProductId(productId);
		return (totalStock == 0);
		
	}

	
	public List<Product> getRelatedProductsByProductId(Long productId) {
		
		Product product = prodRepository.findByProductId(productId);
		Long categoryId = product.getCategoryId();
		Integer currentPrice = product.getCurrentPrice();
		
		List<Product> relatedProducts = prodRepository.findRelatedProducts(categoryId, productId, currentPrice)
										.stream().map(rp -> withImages(rp)).collect(Collectors.toList());
		
		return relatedProducts;
		
	}
	
	private Product withImages(Product product) {
		List<Image> images = imgRepository.findByProductId(product.getProductId());
		List<String> imageUrls = images.stream().map( Image::getImageUrl ).collect(Collectors.toList());
		product.setImageUrls(imageUrls);
		return product;
	}
	
	
	private Product withStockInfo(Product product) {
		List<Stock> stockInfo = stockRepository.findByProductId(product.getProductId())
								.stream().map(si -> withSizeCode(si)).collect(Collectors.toList());
		product.setStockInfo(stockInfo);
		return product;
	}
	
	
	private Product withImagesAndStockInfo(Product product) {
		return withImages(withStockInfo(product));
	}
	
	
	private Stock withSizeCode(Stock stock) {
		stock.setSizeCode(stock.getSizeId());
		return stock;
	}


	public Integer getGlobalMaxPrice() {
		String sql = "SELECT MAX(currentPrice) FROM Product";
		return (Integer) entityManager.createQuery(sql).getSingleResult();
	}


	public Integer getGlobalMinPrice() {
		String sql = "SELECT MIN(currentPrice) FROM Product";
		return (Integer) entityManager.createQuery(sql).getSingleResult();
	}


	@SuppressWarnings("unchecked")
	public List<Product> getFilteredProducts(FilterDto filterDto) {
		String sql = filterDto.getQueryOf();
		List<Product> products = (List<Product>) entityManager.createNativeQuery(sql, Product.class).getResultList();
		return products.stream().map(rp -> withImages(rp)).collect(Collectors.toList());
	}


	
}
