package com.msapay.banking.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {//온전히 뱅킹 서비스를 위한 membership
    private String membershipId;
    private String name;
    private  String address;
    private  boolean isValid;
    private  String email;
    private  boolean isCorp;
}
