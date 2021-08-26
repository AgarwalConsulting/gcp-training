package com.ecommerce.Service;

import org.springframework.stereotype.Service;

import com.ecommerce.Dto.BillingDto;

@Service
public class PaymentService {

	// Payment activities are out of the scope of this project
	// However, separation of concerns should still not be overlooked
	// So, this is why this small function belongs to a class of its own
	// Rather than being implemented in the checkout service
	public boolean tryPayment(BillingDto billingDto) {
		return (billingDto.getCcn().replaceAll(" ", "").equals("1234123412341234"));
	}
	
}
