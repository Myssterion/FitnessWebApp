package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.PaymentDto;
import com.springboot.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayments(){
		List<PaymentDto> payments = paymentService.findAllPayments();
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}

}
