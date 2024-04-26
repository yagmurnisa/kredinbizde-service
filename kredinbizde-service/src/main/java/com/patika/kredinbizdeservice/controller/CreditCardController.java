package com.patika.kredinbizdeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.service.CreditCardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CreditCardController {

	private final CreditCardService creditCardService;
		
	@GetMapping("/bank/{bankId}")
	public List<CreditCard> getCardsByBank(@PathVariable Long bankId) {
		return creditCardService.getByBank(bankId);
	}
	@GetMapping
	public List<CreditCard> getAllCards() {
		return creditCardService.getAll();
	}
	@GetMapping("/{id}")
	public CreditCard getCardById(@PathVariable Long id) {
		return creditCardService.getById(id);

	}
}
