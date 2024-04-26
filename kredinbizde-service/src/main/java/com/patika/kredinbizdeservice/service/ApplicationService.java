package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.FirstBankClient;
import com.patika.kredinbizdeservice.client.dto.request.FirstBankCardAppReq;
import com.patika.kredinbizdeservice.client.dto.request.FirstBankLoanAppReq;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.CardApplicationRequest;
import com.patika.kredinbizdeservice.dto.request.LoanApplicationRequest;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.LoanApplication;
import com.patika.kredinbizdeservice.model.CardApplication;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.LoanApplicationRepository;
import com.patika.kredinbizdeservice.repository.CardApplicationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CardApplicationRepository cardApplicationRepository;
    private final ApplicationConverter applicationConverter;
    private final UserService userService;
    private final LoanService loanService;
    private final CreditCardService cardService;
    private final FirstBankClient firstBankClient;

    public LoanApplication createLoanApplication(LoanApplicationRequest request) {

        User user = userService.getUserById(request.getUserId());

        Loan loan = loanService.getById(request.getLoanId());
        
        //convert the application request to application entity
        LoanApplication application = applicationConverter.toLoanApplication(request, user, loan);

        ApplicationResponse applicationResponse = handleLoanApplication(request, loan);

        if (applicationResponse == null) {
        	throw new KredinbizdeException(ExceptionMessages.APPLICATON_CREATION_ERROR);
        }
        
        LoanApplication savedApplication = loanApplicationRepository.save(application);

        return savedApplication;
    }
    
    public CardApplication createCardApplication(CardApplicationRequest request) {
    	
    	User user = userService.getUserById(request.getUserId());      
    	
        CreditCard card = cardService.getById(request.getCardId());
        
        CardApplication application = applicationConverter.toCardApplication(request, user, card);

        ApplicationResponse applicationResponse = handleCardApplication(request, card);

        if (applicationResponse == null) {
        	throw new KredinbizdeException(ExceptionMessages.APPLICATON_CREATION_ERROR);
        }

        CardApplication savedApplication = cardApplicationRepository.save(application);

        return savedApplication;
    }

    public LoanApplication getLoanById(Long id) {
    	
    	Optional<LoanApplication> foundApp = loanApplicationRepository.findById(id);
    	LoanApplication application = foundApp.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.APPLICATON_NOT_FOUND));
    	return application;
    }
    
    public CardApplication getCardById(Long id) {
    	
    	Optional<CardApplication> foundApp = cardApplicationRepository.findById(id);
    	CardApplication application = foundApp.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.APPLICATON_NOT_FOUND));
    	return application;
    }
    
    public List<LoanApplication> getLoansByUser(Long id) {
    	List<LoanApplication> loans = loanApplicationRepository.findByUserId(id);
    	return loans;
    }
    
    public List<CardApplication> getCardsByUser(Long id) {
    	List<CardApplication> cards = cardApplicationRepository.findByUserId(id);
    	return cards;
    }
    
    private ApplicationResponse handleLoanApplication(LoanApplicationRequest request, Loan loan) {

    	ApplicationResponse applicationResponse = null;
    	if (loan.getBank().getId().equals(1L)) {
    		applicationResponse = firstBankClient.createLoanApplication(prepareFirstBankLoanApp(request));
    	}
    	return applicationResponse;
    }
    
    private ApplicationResponse handleCardApplication(CardApplicationRequest request, CreditCard card) {
    	
    	ApplicationResponse applicationResponse = null;
    	if (card.getBank().getId().equals("1")) {
    		applicationResponse = firstBankClient.createCardApplication(prepareFirstBankCardApp(request));
    	}

    	return applicationResponse;
    }

    private FirstBankLoanAppReq prepareFirstBankLoanApp(LoanApplicationRequest request) {
    	
        FirstBankLoanAppReq applicationRequest = new FirstBankLoanAppReq();

        applicationRequest.builder()
        	.tcId(request.getTcId())
        	.email(request.getEmail())
        	.amount(request.getAmount())
        	.installment(request.getInstallment())
        	.loanId(request.getLoanId()).build();      
       
        return applicationRequest;
    }
    
    private FirstBankCardAppReq prepareFirstBankCardApp(CardApplicationRequest request) {
	
	    FirstBankCardAppReq applicationRequest = new FirstBankCardAppReq();
	
	    applicationRequest.builder()
	    	.tcId(request.getTcId())
	    	.email(request.getEmail())
	    	.cardId(request.getCardId());
	    
	    return applicationRequest;
    }
}
