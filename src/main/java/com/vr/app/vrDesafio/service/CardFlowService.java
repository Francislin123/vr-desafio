package com.vr.app.vrDesafio.service;

import com.vr.app.vrDesafio.controller.request.CardBalanceRequest;
import com.vr.app.vrDesafio.controller.request.CardRequest;
import com.vr.app.vrDesafio.controller.response.BalanceResponse;
import com.vr.app.vrDesafio.controller.response.CardResponse;

public interface CardFlowService {
    CardResponse createdCard(CardRequest cardRequest);

    boolean cardValidation(CardRequest cardRequest);

    BalanceResponse checkingCardBalance(String cardNumber);

    String performTransaction(CardBalanceRequest cardBalanceRequest);
}
