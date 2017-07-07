package com.amastigote.mailing.service.util;

import java.util.List;

public class MassiveMailingTaskDetail {
    private List<String> destinations;
    private String htmlBody;
    private String sender;
    private String subject;

    public String getSender() {
        return sender;
    }

    public MassiveMailingTaskDetail setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MassiveMailingTaskDetail setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public MassiveMailingTaskDetail setDestinations(List<String> destinations) {
        this.destinations = destinations;
        return this;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public MassiveMailingTaskDetail setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
        return this;
    }
}
