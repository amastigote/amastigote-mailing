package com.amastigote.mailservice;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quartz.SchedulerException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static String SENDER_SERVER_ADDR;
    private static String SENDER_USR;
    private static String SENDER_PWD;
    private static int SENDER_SERVER_PORT;
    private static String SERVICE_KEY;
    private static short TRIGGER_HOUR;
    private static short TRIGGER_MIN;
    private static String DB_ADDR;
    private static String DB_USR;
    private static String DB_PWD;

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
//        FileReader fileReader = new FileReader("configuration.json");

        /*
         * For dev
         */
        FileReader fileReader = new FileReader("configuration-dev.json");

        JSONObject jsonObject = new JSONObject(new JSONParser()
                .parse(fileReader).toString());
        SERVICE_KEY = jsonObject.getString("service_key");
        JSONObject senderConfJSONObj = jsonObject.getJSONObject("sender");
        SENDER_PWD = senderConfJSONObj.getString("password");
        SENDER_USR = senderConfJSONObj.getString("username");
        SENDER_SERVER_ADDR = senderConfJSONObj.getString("smtp_server_addr");
        SENDER_SERVER_PORT = senderConfJSONObj.getInt("smtp_server_port");
        JSONObject schedulerConfJSONObj = jsonObject.getJSONObject("scheduler");
        TRIGGER_HOUR = (short) schedulerConfJSONObj.getInt("trigger_everyday_at_hour_in_24h");
        TRIGGER_MIN = (short) schedulerConfJSONObj.getInt("trigger_everyday_at_minute");
        JSONObject databaseConfJSONObj = jsonObject.getJSONObject("database");
        DB_ADDR = databaseConfJSONObj.getString("address");
        DB_USR = databaseConfJSONObj.getString("username");
        DB_PWD = databaseConfJSONObj.getString("password");
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

    public static String getSenderServerAddr() {
        return SENDER_SERVER_ADDR;
    }

    public static int getSenderServerPort() {
        return SENDER_SERVER_PORT;
    }

    public static short getTriggerHour() {
        return TRIGGER_HOUR;
    }

    public static short getTriggerMin() {
        return TRIGGER_MIN;
    }

    public static String getDbAddr() {
        return DB_ADDR;
    }

    public static String getDbPwd() {
        return DB_PWD;
    }

    public static String getDbUsr() {
        return DB_USR;
    }
}
