package com.amastigote.mailservice;

import com.amastigote.mailservice.server.MailingRequestServer;
import com.amastigote.mailservice.service.MailingTaskManager;
import com.amastigote.mailservice.service.util.ScheduleManager;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length > 0)
                ConfigurationManager.loadConfiguration(args[0]);
            else
                ConfigurationManager.loadConfiguration();
            MailingTaskManager.startUp();
            MailingRequestServer.start(8081);
            ScheduleManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
