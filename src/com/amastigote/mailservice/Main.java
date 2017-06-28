package com.amastigote.mailservice;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quartz.SchedulerException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static String SENDER_USR;
    private static String SENDER_PWD;
    private static String SERVICE_KEY;

    public static void main(String[] args) {
        try {
            loadConfigurations();
            SchedulerManager.start();

            /*
             * Handle all the checked exceptions here.
             */
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found.");
            System.exit(-1);
        } catch (ParseException e) {
            System.out.println("Configuration file parsing error.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("I/O exception.");
            System.exit(-1);
        } catch (SchedulerException e) {
            System.out.println("Error when scheduling jobs.");
            System.exit(-1);
        }
    }

    private static void loadConfigurations() throws IOException, ParseException {
        FileReader fileReader = new FileReader("configuration.json");
        JSONObject jsonObject = new JSONObject(new JSONParser()
                .parse(fileReader).toString());
        SERVICE_KEY = jsonObject.getString("service_key");
        JSONObject senderConfJSONObj = jsonObject.getJSONObject("sender_account");
        SENDER_PWD = senderConfJSONObj.getString("password");
        SENDER_USR = senderConfJSONObj.getString("username");
    }

    /*
     * Global attributes are read-only outside the Main Class
     */
    public static String getSenderUsr() {
        return SENDER_USR;
    }

    public static String getSenderPwd() {
        return SENDER_PWD;
    }

    public static String getServiceKey() {
        return SERVICE_KEY;
    }
}
