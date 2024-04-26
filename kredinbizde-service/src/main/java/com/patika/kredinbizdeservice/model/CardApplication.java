package com.patika.kredinbizdeservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "card_applications")
public class CardApplication extends Audit implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CreditCard card;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;

}
