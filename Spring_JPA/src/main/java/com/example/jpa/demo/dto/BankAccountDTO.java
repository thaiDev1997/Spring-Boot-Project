package com.example.jpa.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {

    private Long id;

    private String fullName;

    private double balance;
}
