package com.patika.kredinbizdeservice.dto.request;

import java.math.BigDecimal;

import com.patika.kredinbizdeservice.model.Bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardApplicationRequest {

	private Long userId;

	private String email;
	
	private String tcId;
    
    private Long cardId;	

    private Bank bank;
}
