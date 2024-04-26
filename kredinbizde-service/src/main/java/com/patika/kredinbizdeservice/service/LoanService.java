package com.patika.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.repository.BankRepository;
import com.patika.kredinbizdeservice.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {

	private final LoanRepository loanRepository;
	
	public List<Loan> getAll() {
		return loanRepository.findAll();
	}
	
	public List<Loan> getByBank(Long bankId) {
		return loanRepository.findByBankId(bankId);
	}
	
	public Loan getById(Long id) {
		Optional<Loan> foundBank = loanRepository.findById(id);
		Loan bank = foundBank.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.LOAN_NOT_FOUND));
		return bank;
	}
	
}