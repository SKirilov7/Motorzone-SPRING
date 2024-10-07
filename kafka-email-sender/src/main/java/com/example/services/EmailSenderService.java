package com.example.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    @Value("${spring.mail.from}")
    private String fromEmail;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);  // Set true for HTML content
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(fromEmail);

            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            String a = "abc";
        }
    }

    @Recover
    public void recover(MessagingException e, String to, String subject, String body) {
        logger.error("Failed to send email to {} after retries: {}", to, e.getMessage());
    }

}
