package com.patika.kredinbizdeservice.converter;

import com.patika.kredinbizdeservice.dto.request.CardApplicationRequest;
import com.patika.kredinbizdeservice.dto.request.LoanApplicationRequest;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.model.LoanApplication;
import com.patika.kredinbizdeservice.model.Audit;
import com.patika.kredinbizdeservice.model.CardApplication;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationConverter {

    public LoanApplication toLoanApplication(LoanApplicationRequest request, User user, Loan loan) {
    	
        LoanApplication application = new LoanApplication();
        application.setAmount(request.getAmount());
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setInstallment(request.getInstallment());
        application.setLoan(loan);
        application.setUser(user);
        application.setCreatedDate(LocalDate.now());
        application.setUpdatedDate(LocalDate.now());
        
        return application;
    }
    
    public CardApplication toCardApplication(CardApplicationRequest request, User user, CreditCard card) {
    	
    	CardApplication application = new CardApplication();
    	application.builder()
	    	.card(card)
	        .user(user)
	        .applicationStatus(ApplicationStatus.INITIAL).build();
        application.setCreatedDate(LocalDate.now());
        application.setUpdatedDate(LocalDate.now());
        
        return application;
    }
}
