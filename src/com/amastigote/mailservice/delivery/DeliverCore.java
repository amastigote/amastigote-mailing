package com.amastigote.mailservice.delivery;

import com.amastigote.mailservice.util.Configuration;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import java.util.List;

class DeliverCore {

    synchronized static void deliver(List<String> receiverEmails, String HTMLBody) {
        receiverEmails.forEach(e -> deliverTo(e, HTMLBody));
    }

    private static void deliverTo(String receiverEmailAddress, String HTMLBody) {
        new Mailer(
                Configuration.getSenderServerAddr(),
                Configuration.getSenderServerPort(),
                Configuration.getSenderUsr(),
                Configuration.getSenderPwd()).sendMail(buildMail(receiverEmailAddress, HTMLBody));
    }

    private static Email buildMail(String receiverEmailAddress, String HTMLBody) {
        return new EmailBuilder()
                .from("Amastigote Daily", Configuration.getSenderUsr())
                .to("", receiverEmailAddress)
                .subject("Pages Collected Today on Amastigote")
                .textHTML(HTMLBody)
                .build();
    }
}
