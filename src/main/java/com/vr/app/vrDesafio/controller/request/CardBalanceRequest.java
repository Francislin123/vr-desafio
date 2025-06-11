package com.vr.app.vrDesafio.controller.request;

import lombok.Data;

@Data
public class CardBalanceRequest {
    private String password;
    private String cardNumber;
    private Double cardBalance;
}
