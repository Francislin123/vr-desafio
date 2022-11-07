package com.vr.app.vrDesafio.service;

import com.vr.app.vrDesafio.controller.request.CardRequest;
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

        final CardResponse cardResponse = CardResponse.builder()
                .password(cardRequest.getPassword())
                .cardNumber(cardRequest.getCardNumber()).build();

        this.persistCard(getBuildCard(cardRequest));

        return cardResponse;
    }

    @Override
    public boolean cardValidation(CardRequest cardRequest) {
        return Objects.isNull(cardRepository.findByCardNumber(cardRequest.getCardNumber()));
    }

    @Override
    public double checkingCardBalance(String cardNumber) {

        final CardEntity card = cardRepository.findByCardNumber(cardNumber);

        if (Objects.isNull(card)) {
            return 0.0;
        }

        return card.getCardBalance();
    }

    private void persistCard(CardEntity cardEntity) {
        cardEntity.setCardBalance(500.0);
        CardEntity card = cardRepository.saveAndFlush(cardEntity);
        log.info("cardEntity={} message=insert_successfully", card);
    }

    private boolean cardBalanceValidation(final double cardBalance,
                                          final double AmountToWithdraw,
                                          final CardEntity cardEntity) {
        if (cardBalance < AmountToWithdraw) return false;
        else {
            final double balance = cardBalance - AmountToWithdraw;
            cardEntity.setCardBalance(balance);
            this.persistCard(cardEntity);
            return true;
        }
    }

    private CardEntity getBuildCard(CardRequest cardRequest) {
        return CardEntity.builder()
                .cardNumber(cardRequest.getCardNumber())
                .password(cardRequest.getPassword())
                .cardBalance(500.0).build();
    }
}
