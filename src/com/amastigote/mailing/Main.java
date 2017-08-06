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
            MailingRequestServer.startUp(8081);
            ScheduleManager.startUp();
        } catch (Exception e) {
            e.printStackTrace();

            // TODO free exception handling down to certain class,
            // TODO main class only handles exception thrown during startup
            // TODO and exit the system if that happens
            System.exit(0);
        }
    }
}
