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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/blacklist")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    @PostMapping("")
    public ResponseEntity<Object> addBlacklistNumber(@RequestBody BlacklistPhoneNumberRequest phoneNumbers) throws BadRequestException, NotFoundException {
        try{
            return ResponseEntity.ok().body(blacklistService.addBlacklistNumbers(phoneNumbers.getPhone_numbers()));
        }catch (BadRequestException e){
            return  ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code("INVAlID_REQUEST").message(e.getMessage()).build());
        }catch (NotFoundException e){
            return ResponseEntity.internalServerError().body(Error.builder().status(HttpStatus.BAD_REQUEST).code("TIME_OUT").message(e.getMessage()).build());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteBlacklistNumber(@RequestBody BlacklistPhoneNumberRequest phoneNumbers) throws NotFoundException, BadRequestException{
        try{
            return  ResponseEntity.ok().body(blacklistService.deleteBlacklistNumbers(phoneNumbers.getPhone_numbers()));
        }catch (BadRequestException e){
            return  ResponseEntity.badRequest().body(Error.builder().status(HttpStatus.BAD_REQUEST).code("INVAlID_REQUEST").message(e.getMessage()).build());
        }catch (NotFoundException e){
            return ResponseEntity.internalServerError().body(Error.builder().status(HttpStatus.BAD_REQUEST).code("TIME_OUT").message(e.getMessage()).build());
        }
    }

    @GetMapping("")
    public ResponseEntity<BlackListResponse> getAllBlacklistedNumbers() throws BadRequestException{
        try{
            return ResponseEntity.ok().body( blacklistService.getBlacklistNumbers());
        }catch (BadRequestException e){
            return  ResponseEntity.badRequest().body(BlackListResponse.builder().error(e.getMessage()).build());
        }
    }
}
