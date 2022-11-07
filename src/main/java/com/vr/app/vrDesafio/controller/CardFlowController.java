package com.vr.app.vrDesafio.controller;

import com.vr.app.vrDesafio.controller.request.CardBalanceRequest;
import com.vr.app.vrDesafio.controller.request.CardRequest;
import com.vr.app.vrDesafio.controller.response.CardResponse;
import com.vr.app.vrDesafio.service.CardFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Api
@RestController
public class CardFlowController {

    @Autowired
    private CardFlowService cardFlowService;

    @ApiOperation(value = "Created and validate card", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Card created successfully", response = ResponseEntity.class),
            @ApiResponse(code = 422, message = "If the card already exists")})
    @RequestMapping(path = "/cards", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> cardService(@Valid @RequestBody CardRequest cardRequest) {

        final var cardValidation = cardFlowService.cardValidation(cardRequest);

        if (!cardValidation) {
            return new ResponseEntity<>(CardResponse.builder()
                    .cardNumber(cardRequest.getCardNumber())
                    .password(cardRequest.getPassword()).build(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(cardFlowService.createdCard(cardRequest), new HttpHeaders(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Checking card balance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Balance on the card", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "If the card does not exist")})
    @RequestMapping(value = "{cardNumber}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkingCardBalance(@PathVariable("cardNumber") String cardNumber) {

        final var balance = cardFlowService.checkingCardBalance(cardNumber);

        if (balance <= 0.0) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Created and validate card", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Card created successfully", response = ResponseEntity.class),
            @ApiResponse(code = 422, message = "If the card already exists")})
    @RequestMapping(path = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> performTransaction(@Valid @RequestBody CardBalanceRequest cardBalanceRequest) {

        final String response = cardFlowService.performTransaction(cardBalanceRequest);

        if (response != null) {
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
