package com.meesho.notificationservice.controllers;

import com.meesho.notificationservice.exceptions.BadRequestException;
import com.meesho.notificationservice.exceptions.NotFoundException;
import com.meesho.notificationservice.models.Error;
import com.meesho.notificationservice.models.request.BlacklistPhoneNumberRequest;
import com.meesho.notificationservice.models.response.BlackListResponse;
import com.meesho.notificationservice.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.meesho.notificationservice.constants.Constants.INVAlID_REQUEST;
import static com.meesho.notificationservice.constants.Constants.NOT_FOUND;

@RestController
@RequestMapping("/v1/blacklist")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    @PostMapping("")
    public ResponseEntity<Object> addBlacklistNumber(@RequestBody BlacklistPhoneNumberRequest phoneNumbers) {
        try {
            return ResponseEntity.ok().body(blacklistService.addBlacklistNumbers(phoneNumbers.getPhone_numbers()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(INVAlID_REQUEST).message(e.getMessage()).build());
        } catch (NotFoundException e) {
            return ResponseEntity.internalServerError().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(NOT_FOUND).message(e.getMessage()).build());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteBlacklistNumber(@RequestBody BlacklistPhoneNumberRequest phoneNumbers) {
        try {
            return ResponseEntity.ok().body(blacklistService.deleteBlacklistNumbers(phoneNumbers.getPhone_numbers()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(INVAlID_REQUEST).message(e.getMessage()).build());
        } catch (NotFoundException e) {
            return ResponseEntity.internalServerError().body(Error.builder().status(HttpStatus.BAD_REQUEST).code(NOT_FOUND).message(e.getMessage()).build());
        }
    }

    @GetMapping("")
    public ResponseEntity<BlackListResponse> getAllBlacklistedNumbers() {
        try {
            return ResponseEntity.ok().body(blacklistService.getBlacklistNumbers());
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(BlackListResponse.builder().error(e.getMessage()).build());
        }
    }
}
