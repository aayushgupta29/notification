package com.meesho.notificationservice.controllers;

import com.meesho.notificationservice.exceptions.BadRequestException;
import com.meesho.notificationservice.exceptions.NotFoundException;
import com.meesho.notificationservice.models.SmsRequest;
import com.meesho.notificationservice.service.SmsRequestService;
import com.meesho.notificationservice.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.meesho.notificationservice.constants.Constants.INVAlID_REQUEST;
import static com.meesho.notificationservice.constants.Constants.NOT_FOUND;

@RestController
@RequestMapping("/v1/sms")
public class SmsController {
    @Autowired
    private SmsRequestService smsRequestService;

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public ResponseEntity<Object> sendSms(@RequestBody SmsRequest smsRequest) {
        try {
            return ResponseEntity.ok(smsRequestService.sendSms(smsRequest));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(INVAlID_REQUEST).message(e.getMessage()).build());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> findSmsRequest(@PathVariable int id) {
        try {
            return ResponseEntity.ok(smsRequestService.findSmsRequest(id));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(INVAlID_REQUEST).message(e.getMessage()).build());
        } catch (NotFoundException e) {
            return ResponseEntity.internalServerError().body(Error.builder().status(HttpStatus.NOT_FOUND).code(NOT_FOUND).message(e.getMessage()).build());
        }
    }
}
