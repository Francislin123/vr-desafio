package com.vr.app.vrDesafio.controller.response;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class BalanceResponse {
    private String cardNumber;
    private Double cardBalance;;
}
