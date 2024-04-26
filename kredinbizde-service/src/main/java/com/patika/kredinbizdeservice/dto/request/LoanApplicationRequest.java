package com.patika.kredinbizdeservice.dto.request;

import java.math.BigDecimal;

import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.Loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequest {

	private Long userId;
	
    private String email;
    
	private String tcId;
    
    private Long loanId;
    
    private BigDecimal amount;

    private Integer installment;
}
