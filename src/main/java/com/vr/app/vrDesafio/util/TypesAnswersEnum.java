package com.vr.app.vrDesafio.util;

public enum TypesAnswersEnum {

    CARD_NON_EXISTENT("CARTAO_INEXISTENTE"),
    INSUFFICIENT_FUNDS("SALDO_INSUFICIENTE"),
    INVALID_PASSWORD("SENHA_INVALIDA");

    private String typesAnswersEnum;

    TypesAnswersEnum(final String typesAnswersEnum) {
        this.typesAnswersEnum = typesAnswersEnum;
    }

    public String getTypesAnswersEnum() {
        return typesAnswersEnum;
    }
}
