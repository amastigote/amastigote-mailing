package com.amastigote.mailing;

import com.amastigote.mailing.server.MailingRequestServer;
import com.amastigote.mailing.service.MailingTaskManager;
import com.amastigote.mailing.service.util.ScheduleManager;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length > 0)
                ConfigurationManager.load(args[0]);
            else
                ConfigurationManager.load();
            MailingTaskManager.startUp();
            MailingRequestServer.start(8081);
            ScheduleManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
