package com.patika.kredinbizdeservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = false, nullable = false)
    private String password;
    
    @Transient
    private String password2;

    @Column(name = "phone_number", unique = false, nullable = true)
    private String phoneNumber;

    @Column(name = "is_active", unique = false, nullable = true)
    private Boolean isActive;

    @OneToOne
    @JoinColumn(unique = false,  nullable = true)
    private Address address;

}
