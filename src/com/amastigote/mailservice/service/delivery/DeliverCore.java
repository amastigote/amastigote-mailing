package com.amastigote.mailservice.service.delivery;

import com.amastigote.mailservice.Configuration;
import com.amastigote.mailservice.service.util.SingleMailingTaskDetail;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

public class DeliverCore {

    public static void deliver(SingleMailingTaskDetail singleMailingTaskDetail) {
        new Mailer(
                Configuration.getSenderServerAddr(),
                Configuration.getSenderServerPort(),
                Configuration.getSenderUsr(),
                Configuration.getSenderPwd())
                .sendMail(buildMail(singleMailingTaskDetail));
    }

    private static Email buildMail(SingleMailingTaskDetail singleMailingTaskDetail) {
        return new EmailBuilder()
                .from(singleMailingTaskDetail.getSender(), Configuration.getSenderUsr())
                .to("", singleMailingTaskDetail.getDestination())
                .subject(singleMailingTaskDetail.getSubject())
                .textHTML(singleMailingTaskDetail.getHtmlBody())
                .build();
    }
}
