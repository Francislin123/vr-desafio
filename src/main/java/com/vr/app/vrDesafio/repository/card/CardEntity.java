package com.vr.app.vrDesafio.repository.card;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tab_card")
public class CardEntity {

    @Id
    @GeneratedValue(generator = "tab_card_uuid_generator")
    @GenericGenerator(name = "tab_card_uuid_generator", strategy = "uuid2")
    private UUID id;

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
    public CardEntity(UUID id, String password, String cardNumber, Double cardBalance) {
        this.id = id;
        this.password = password;
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardBalance='" + cardBalance + '\'' +
                '}';
    }
}
