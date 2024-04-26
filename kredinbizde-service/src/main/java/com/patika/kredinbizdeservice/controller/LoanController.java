package com.patika.kredinbizdeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/loans")
@RequiredArgsConstructor
public class LoanController {

	private final LoanService loanService;
	
	@GetMapping("/bank/{bankId}")
	public List<Loan> getLoansByBank(@PathVariable Long bankId) {
		return loanService.getByBank(bankId);
	}
	@GetMapping
	public List<Loan> getAllLoans() {
		return loanService.getAll();
	}

	@GetMapping("/{id}")
	public Loan getLoanById(@PathVariable Long id) {
		return loanService.getById(id);

	}
}
