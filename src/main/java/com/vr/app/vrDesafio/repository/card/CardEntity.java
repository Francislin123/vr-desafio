package com.vr.app.vrDesafio.repository.card;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tab_card")
public class CardEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "card_password")
    private String password;

    @Column(name = "card_numb")
    private String cardNumber;

    @Column(name = "card_balance")
    private Double cardBalance;

    @Tolerate
    public CardEntity() {
        // Default constructor for hibernate
    }

    @Builder
    public CardEntity(Long id, String password, String cardNumber, Double cardBalance) {
        this.id = id;
        this.password = password;
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;
    }
}
