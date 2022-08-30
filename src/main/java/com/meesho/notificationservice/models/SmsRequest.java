package com.meesho.notificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import static com.meesho.notificationservice.constants.Constants.SMS_REQUEST_TABLE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = SMS_REQUEST_TABLE)
public class SmsRequest {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String message;
    private String status;
    @Column(name = "failure_code")
    private String failureCode;
    @Column(name = "failure_comments")
    private String failureComments;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "updated_at")
    private long updatedAt;
}
