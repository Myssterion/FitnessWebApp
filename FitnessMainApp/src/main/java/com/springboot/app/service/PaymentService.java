package com.springboot.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.dto.PaymentDto;
import com.springboot.app.model.Payment;
import com.springboot.app.repository.PaymentRepository;
import com.springboot.app.utility.EntityToDto;

@Service
@Transactional
public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public List<PaymentDto> findAllPayments(){
		List<Payment> payments = paymentRepository.findAll();
		return payments.stream()
						.map(EntityToDto::ConvertToDto)
						.collect(Collectors.toList());
	}

	public Payment addPayment(PaymentDto paymentDto) {
		Payment payment = new Payment();
		payment.setMethod(paymentDto.getMethod());
		
		return paymentRepository.save(payment);
	}


}
