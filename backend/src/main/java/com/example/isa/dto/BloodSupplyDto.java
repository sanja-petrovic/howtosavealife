package com.example.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloodSupplyDto {
    private UUID requestId;
    private String Bank;
    private String BloodType;
    private String RhFactor;
    private double Amount;
}
