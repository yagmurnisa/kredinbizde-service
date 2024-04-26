package com.patika.kredinbizdeservice.client.dto.request;

import java.math.BigDecimal;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FirstBankCardAppReq {

	private String email;
	
	private String tcId;
    
    private Long cardId;	
}
