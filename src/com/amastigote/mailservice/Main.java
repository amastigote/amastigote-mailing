package com.amastigote.mailservice;

import com.amastigote.mailservice.util.SchedulerManager;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    private static int lastId;

    public static void main(String[] args) {
        try {
            loadConfigurations();
            SchedulerManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadConfigurations() throws IOException, ParseException {
        /*
         * Uncomment the following code in product-env.
         */
        // FileReader fileReader = new FileReader("configuration.json");

        /*
         * For dev-env.
         */
        FileReader fileReader = new FileReader("configuration-dev.json");

        JSONObject jsonObject = new JSONObject(new JSONParser()
                .parse(fileReader).toString());
        SERVICE_KEY = jsonObject.getString("service_key");
        lastId = jsonObject.getInt("begin_from_page_id");
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

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Main.lastId = lastId;
    }
}
