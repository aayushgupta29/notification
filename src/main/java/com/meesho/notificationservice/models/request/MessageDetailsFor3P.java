package com.meesho.notificationservice.models.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MessageDetailsFor3P {

    private String deliverychannel;
    private Channels channels;
    private List<Destination> destination;

    @Data
    @Builder
    public static class Channels {
        private Sms sms;
    }

    @Data
    @Builder
    public static class Sms {
        private String text;
    }

    @Data
    @Builder
    public static class Destination {
        private List<String> msisdn;
        private String correlationId;
    }
}
