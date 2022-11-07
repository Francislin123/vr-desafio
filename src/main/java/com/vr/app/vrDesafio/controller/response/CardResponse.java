package com.vr.app.vrDesafio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardResponse {
    private String password;
    private String cardNumber;
}
