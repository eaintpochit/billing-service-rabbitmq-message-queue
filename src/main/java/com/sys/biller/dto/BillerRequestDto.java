package com.sys.biller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillerRequestDto {
    private String mobileNumber;
    private Double amount;
}
