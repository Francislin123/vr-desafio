package com.vr.app.vrDesafio.service;

import com.vr.app.vrDesafio.controller.request.CardBalanceRequest;
import com.vr.app.vrDesafio.controller.request.CardRequest;
import com.vr.app.vrDesafio.controller.response.BalanceResponse;
import com.vr.app.vrDesafio.controller.response.CardResponse;
import com.vr.app.vrDesafio.repository.CardRepository;
import com.vr.app.vrDesafio.repository.card.CardEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CardFlowServiceImpl implements CardFlowService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public CardResponse createdCard(CardRequest cardRequest) {
        this.persistCard(this.getBuildCard(cardRequest));
        return CardResponse.builder()
                .password(cardRequest.getPassword())
                .cardNumber(cardRequest.getCardNumber()).build();
    }

    @Override
    public boolean cardValidation(final CardRequest cardRequest) {
        return Objects.isNull(this.cardRepository.findByCardNumber(cardRequest.getCardNumber()));
    }

    @Override
    public BalanceResponse checkingCardBalance(final String cardNumber) {
        final CardEntity card = this.cardRepository.findByCardNumber(cardNumber);
        if (Objects.isNull(card)) {
            return BalanceResponse.builder().cardBalance(0.0).cardNumber("").build();
        }
        return BalanceResponse.builder().cardBalance(card.getCardBalance()).cardNumber(card.getCardNumber()).build();
    }

    @Override
    public String performTransaction(final CardBalanceRequest cardBalanceRequest) {

        final CardEntity card = this.cardRepository.findByCardNumber(cardBalanceRequest.getCardNumber());

        if (card != null) {
            return this.validateBalanceAndPassword(card, cardBalanceRequest);
        }

        return "CARTAO_INEXISTENTE";
    }

    private String validateBalanceAndPassword(final CardEntity card, final CardBalanceRequest cardBalanceRequest) {
        if (card.getPassword().equals(cardBalanceRequest.getPassword())) {
            final boolean validationBalance = this.cardBalanceValidation(cardBalanceRequest.getCardBalance(), card);
            if (validationBalance) {
               return null;
            } else {
                return "SALDO_INSUFICIENTE";
            }
        }
        return "SENHA_INVALIDA";
    }

    private void persistCard(final CardEntity cardEntity) {
        CardEntity card = this.cardRepository.saveAndFlush(cardEntity);
        log.info("cardEntity={} message=insert_successfully", card);
    }

    private boolean cardBalanceValidation(final Double amountToWithdraw, final CardEntity cardEntity) {

        if (this.isNegative(amountToWithdraw)) return false;

        if (cardEntity.getCardBalance() < amountToWithdraw)
            return false;
         else {
            final Double cardBalance = cardEntity.getCardBalance() - amountToWithdraw;
            cardRepository.saveAndFlush(CardEntity.builder()
                    .id(cardEntity.getId())
                    .cardNumber(cardEntity.getCardNumber())
                    .password(cardEntity.getPassword())
                    .cardBalance(cardBalance)
                    .build());
            return true;
        }
    }

    private boolean isNegative(final double cardBalance) {
        return Double.doubleToRawLongBits(cardBalance) < 0;
    }

    private CardEntity getBuildCard(final CardRequest cardRequest) {
        return CardEntity.builder()
                .cardNumber(cardRequest.getCardNumber())
                .password(cardRequest.getPassword())
                .cardBalance(500.0).build();
    }
}
