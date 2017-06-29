package com.amastigote.mailservice;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import java.util.List;

public class DeliveringCore {

    public synchronized static void deliver(List<String> receiverEmails, String HTMLBody) {
        receiverEmails.forEach(e -> deliverTo(e, HTMLBody));
    }

    private synchronized static void deliverTo(String receiverEmailAddress, String HTMLBody) {
        new Mailer(
                Main.getSenderServerAddr(),
                Main.getSenderServerPort(),
                Main.getSenderUsr(),
                Main.getSenderPwd()).sendMail(buildMail(receiverEmailAddress, HTMLBody));
    }

    private static Email buildMail(String receiverEmailAddress, String HTMLBody) {
        return new EmailBuilder()
                .from("Amastigote Daily", Main.getSenderUsr())
                .to("", receiverEmailAddress)
                .subject("Pages Collected Today on Amastigote")
                .textHTML(HTMLBody)
                .build();
    }
}
