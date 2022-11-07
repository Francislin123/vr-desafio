package com.vr.app.vrDesafio.service;

import com.vr.app.vrDesafio.controller.request.CardBalanceRequest;
import com.vr.app.vrDesafio.controller.request.CardRequest;
import com.vr.app.vrDesafio.controller.response.CardResponse;
import com.vr.app.vrDesafio.repository.card.CardEntity;

public interface CardFlowService {
    CardResponse createdCard(CardRequest cardRequest);

    boolean cardValidation(CardRequest cardRequest);

    double checkingCardBalance(String cardNumber);

    String performTransaction(CardBalanceRequest cardBalanceRequest);
}
