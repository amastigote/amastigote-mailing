package com.amastigote.mailing.service.delivery;

import com.amastigote.mailing.ConfigurationManager;
import com.amastigote.mailing.service.util.SingleMailingTaskDetail;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

public class DeliverCore {

    public static void deliver(SingleMailingTaskDetail singleMailingTaskDetail) {
        new Mailer(
                ConfigurationManager.getSenderServerAddr(),
                ConfigurationManager.getSenderServerPort(),
                ConfigurationManager.getSenderUsr(),
                ConfigurationManager.getSenderPwd())
                .sendMail(buildMail(singleMailingTaskDetail));
    }

    private static Email buildMail(SingleMailingTaskDetail singleMailingTaskDetail) {
        return new EmailBuilder()
                .from(singleMailingTaskDetail.getSender(), ConfigurationManager.getSenderUsr())
                .to("", singleMailingTaskDetail.getDestination())
                .subject(singleMailingTaskDetail.getSubject())
                .textHTML(singleMailingTaskDetail.getHtmlBody())
                .build();
    }
}
