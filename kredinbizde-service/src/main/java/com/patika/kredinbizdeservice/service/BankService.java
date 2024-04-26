package com.patika.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patika.kredinbizdeservice.client.FirstBankClient;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.repository.LoanApplicationRepository;
import com.patika.kredinbizdeservice.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService {

	private final BankRepository bankRepository;
	
	public List<Bank> getAll() {
		return bankRepository.findAll();
	}
	public Bank getById(Long id) {
		Optional<Bank> foundBank = bankRepository.findById(id);
		Bank bank = foundBank.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.BANK_NOT_FOUND));
		return bank;
	}
	public Bank getByName(String name) {
		Optional<Bank> foundBank = bankRepository.findByName(name);
		Bank bank = foundBank.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.BANK_NOT_FOUND));
		return bank;
	}
}

