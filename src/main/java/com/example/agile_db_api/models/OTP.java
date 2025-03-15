package com.example.agile_db_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false,unique = true)
    String email;

    @Column(nullable = false)
    String otp;

}