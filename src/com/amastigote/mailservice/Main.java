package com.amastigote.mailservice;

import com.amastigote.mailservice.util.ConfigurationManager;
import com.amastigote.mailservice.util.ScheduleManager;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length > 0)
                ConfigurationManager.loadConfiguration(args[0]);
            else
                ConfigurationManager.loadConfiguration();
            ScheduleManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
