package com.amastigote.mailing.service.delivery;

import com.amastigote.mailing.ConfigurationManager;
import com.amastigote.mailing.service.util.SingleMailingTaskDetail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class DeliverCore {

    public static void deliver(SingleMailingTaskDetail singleMailingTaskDetail) throws EmailException {
        buildMail(singleMailingTaskDetail).send();
    }

    private static HtmlEmail buildMail(SingleMailingTaskDetail singleMailingTaskDetail) throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(ConfigurationManager.getSenderServerAddr());
        htmlEmail.setSmtpPort(ConfigurationManager.getSenderServerPort());
        htmlEmail.setAuthentication(ConfigurationManager.getSenderUsr(), ConfigurationManager.getSenderPwd());
        htmlEmail.setFrom(ConfigurationManager.getSenderUsr(), singleMailingTaskDetail.getSender());
        htmlEmail.addTo(singleMailingTaskDetail.getDestination());
        htmlEmail.setSubject(singleMailingTaskDetail.getSubject());
        htmlEmail.setHtmlMsg(singleMailingTaskDetail.getHtmlBody());
        return htmlEmail;
    }
}
