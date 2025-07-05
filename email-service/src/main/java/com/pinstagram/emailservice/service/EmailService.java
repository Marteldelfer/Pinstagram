package com.pinstagram.emailservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import email.EmailMessageOuterClass;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    private void sendEmail(String to, String subject, String text, Context context) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String htmlContent = templateEngine.process(text, context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

    @KafkaListener(topics = "email-confirmation", groupId = "email-service")
    public void sendEmailConfirmation(byte[] event) {
        try {
            EmailMessageOuterClass.EmailMessage emailRequest = EmailMessageOuterClass.EmailMessage.parseFrom(event);
            Context context = new Context();
            context.setVariable("token", emailRequest.getPayloadMap().get("token"));
            sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getTemplate(), context);
        } catch (InvalidProtocolBufferException e) {
            logger.error("Error parsing EmailMessage: {}", e.getMessage());
        } catch (MessagingException e) {
            logger.error("Error processing EmailMessage: {}", e.getMessage());
        }
    }






}