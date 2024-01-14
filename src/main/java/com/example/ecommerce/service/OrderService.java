//package com.example.ecommerce.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.example.ecommerce.DTO.order.CheckoutItemDTO;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//import com.stripe.param.checkout.SessionCreateParams.LineItem;
//
//
//
//@Service
//public class OrderService {
//	
//	@Value("${BASE_URL}")
//	private String baseURL;
//	
//	@Value("${STRIPE_SECRET_KEY}")
//	private String secretKey;
//
//	public Session createSession(List<CheckoutItemDTO> checkoutItemDTOs) throws StripeException {
//		//Success and failure URLs
//		String successURL = baseURL + "payment/success";
//		String failureURL = baseURL + "payment/failed";
//		
//		Stripe.apiKey = secretKey;
//		
//		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
//		
//		for(CheckoutItemDTO checkoutItemDTO : checkoutItemDTOs) {
//			sessionItemList.add(createSessionLineItem(checkoutItemDTO));
//		}
//		
//		SessionCreateParams params = SessionCreateParams.builder()
//				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//				.setMode(SessionCreateParams.Mode.PAYMENT)
//				.setCancelUrl(failureURL)
//				.setSuccessUrl(successURL)
//				.addAllLineItem(sessionItemList)
//				.build();
//		
//		return Session.create(params);
//	}
//
//	private LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDTO) {
//		return SessionCreateParams.LineItem.builder()
//				.setPriceData(createPriceData(checkoutItemDTO))
//				.setQuantity(Long.parseLong(String.valueOf(checkoutItemDTO.getQantity())))
//				.build();
//	}
//
//	private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDTO) {
//		return SessionCreateParams.LineItem.PriceData.builder()
//				.setCurrency("usd")
//				.setUnitAmount((long)checkoutItemDTO.getPrice() * 100)
//				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//						.setName(checkoutItemDTO.getProductName()).build()
//						).build();
//	}
//
//}
