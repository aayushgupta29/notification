package com.meesho.notificationservice.models.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SuccessResponseEntity{

    private int request_id;
    private String comment;

}
