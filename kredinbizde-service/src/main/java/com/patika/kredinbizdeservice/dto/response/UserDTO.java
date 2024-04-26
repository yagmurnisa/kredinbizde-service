package com.patika.kredinbizdeservice.dto.response;

import java.time.LocalDate;

import com.patika.kredinbizdeservice.model.Address;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String email;

    private String phoneNumber;

    private Address address;
    
}
