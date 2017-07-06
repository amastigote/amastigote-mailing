package com.amastigote.mailservice.service.util;

public class SingleMailingTaskDetail {

    private String destination;
    private String htmlBody;
    private String sender;
    private String subject;

    public String getDestination() {
        return destination;
    }

    public SingleMailingTaskDetail setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public SingleMailingTaskDetail setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public SingleMailingTaskDetail setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public SingleMailingTaskDetail setSubject(String subject) {
        this.subject = subject;
        return this;
    }
}
