package com.example.motorzone.models.dto.kafka;

public class KafkaSendEmailDTO {

    private String to;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public KafkaSendEmailDTO setTo(String to) {
        this.to = to;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public KafkaSendEmailDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public KafkaSendEmailDTO setBody(String body) {
        this.body = body;
        return this;
    }

}
