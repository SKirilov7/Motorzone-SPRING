package com.example.models;

public class KafkaEmailDTO {

    private String to;
    private String subject;
    private String body;

    public KafkaEmailDTO() {}

    public String getTo() {
        return to;
    }

    public KafkaEmailDTO setTo(String to) {
        this.to = to;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public KafkaEmailDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public KafkaEmailDTO setBody(String body) {
        this.body = body;
        return this;
    }

}
