package com.meesho.notificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.meesho.notificationservice.constants.Constants.BLACKLIST_TABLE;

@Entity
@Table(name = BLACKLIST_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlacklistPhoneNumber {
    @Id
    private String phoneNumber;
}
