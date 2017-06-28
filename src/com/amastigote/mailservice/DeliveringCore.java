package com.amastigote.mailservice;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

public class DeliveringCore {
    public synchronized static void deliverTo(String receriverEmailAddress) {
        new Mailer(
                Main.getSenderServerAddr(),
                Main.getSenderServerPort(),
                Main.getSenderUsr(),
                Main.getSenderPwd()).sendMail(buildForReceiver(receriverEmailAddress));
    }

    private static Email buildForReceiver(String receiverEmailAddress) {
        return new EmailBuilder()
                .from("Amastigote Daily Push Service", Main.getSenderUsr())
                .to("", receiverEmailAddress)
                .subject("Pages Collected Today on Amastigote")
                .text("this is only a test :)")
                .build();
    }
}
