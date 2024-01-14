//package com.example.ecommerce.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.ecommerce.DTO.order.CheckoutItemDTO;
//import com.example.ecommerce.service.OrderService;
//import com.example.ecommerce.service.TokenService;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.example.ecommerce.DTO.order.StripeResponse;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	@Autowired
//	private OrderService orderService;
//	
//	@PostMapping("/create-checkout-session")
//	public ResponseEntity<StripeResponse> chekoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDTOs) 
//			throws StripeException{
//		Session session = orderService.createSession(checkoutItemDTOs);
//		StripeResponse stripeResponse = new StripeResponse(session.getId());
//		return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
//	}
//
//}
