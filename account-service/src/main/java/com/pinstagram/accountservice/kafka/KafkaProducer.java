package com.pinstagram.accountservice.kafka;

import com.pinstagram.accountservice.model.Account;
import email.EmailMessageOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendConfirmationEmail(Account account, String token) {
        EmailMessageOuterClass.EmailMessage emailMessage = EmailMessageOuterClass.EmailMessage.newBuilder()
                .setSubject("Pinstagram email validation")
                .setTemplate("email-confirmation-template")
                .setTo(account.getEmail())
                .putPayload("token", token)
                .build();
        try {
            kafkaTemplate.send("email-confirmation", emailMessage.toByteArray());
        } catch (Exception e) {
            logger.error("Error sending email-confirmation event: {}", e.getMessage());
        }
    }
}
