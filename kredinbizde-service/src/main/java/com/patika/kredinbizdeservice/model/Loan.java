package com.patika.kredinbizdeservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "loans")
public class Loan  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	private String name;

    @Column(name= "max_amount")
    private BigDecimal maxAmount;

    @Column(name= "min_amount")
    private BigDecimal minAmount;

	@Transient
    private Integer installment;
    
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
	@Column(name="interest_rate")
    private Double interestRate;
   
}
