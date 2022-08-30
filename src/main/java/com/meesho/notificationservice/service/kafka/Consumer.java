package com.meesho.notificationservice.service.kafka;

import com.meesho.notificationservice.models.SmsRequest;
import com.meesho.notificationservice.repository.SmsRequestRepository;

import com.meesho.notificationservice.service.BlacklistService;
import com.meesho.notificationservice.service.ThirdPartyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.meesho.notificationservice.constants.Constants.GROUP_ID;
import static com.meesho.notificationservice.constants.Constants.TOPIC;

@Service
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private static final String FAILURE_STATUS = "FAILED";
    private static final String FAILURE_CODE = "ERR_BLACKLIST";
    private static final String FAILURE_COMMENT = "Phone Number is blacklisted";
    @Autowired
    private SmsRequestRepository smsRequestRepository;
    @Autowired
    private BlacklistService blacklistService;
    @Autowired
    private ThirdPartyHandler thirdPartyHandler;

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consumer(int messageId) {
        LOGGER.info(String.format("MESSAGE has been received %s", messageId));
        SmsRequest smsRequest = smsRequestRepository.findById(messageId);
        boolean isPhoneNumberBlacklisted = blacklistService.getStatusOfBlacklistNumber(smsRequest.getPhoneNumber());

        if (isPhoneNumberBlacklisted) {
            LOGGER.info(String.format("Phone no is blacklisted"));
            smsRequest.setUpdatedAt(System.currentTimeMillis());
            smsRequest.setFailureComments(FAILURE_COMMENT);
            smsRequest.setStatus(FAILURE_STATUS);
            smsRequest.setFailureCode(FAILURE_CODE);
            smsRequestRepository.save(smsRequest);
        } else {
            thirdPartyHandler.sendThirdPartySms(smsRequest);
        }
    }
}
