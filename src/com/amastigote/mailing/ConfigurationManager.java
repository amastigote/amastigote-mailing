package com.amastigote.mailing;

import com.amastigote.mailing.service.delivery.DeliverJob;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
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
    private static String MAIL_VALIDATION_SERVER;

    public static String getMailValidationServer() {
        return MAIL_VALIDATION_SERVER;
    }

    private static void setMailValidationServer(String mailValidationServer) {
        MAIL_VALIDATION_SERVER = mailValidationServer;
    }

    public static String getSenderServerAddr() {
        return SENDER_SERVER_ADDR;
    }

    private static void setSenderServerAddr(String senderServerAddr) {
        SENDER_SERVER_ADDR = senderServerAddr;
    }

    public static String getSenderUsr() {
        return SENDER_USR;
    }

    private static void setSenderUsr(String senderUsr) {
        SENDER_USR = senderUsr;
    }

    public static String getSenderPwd() {
        return SENDER_PWD;
    }

    private static void setSenderPwd(String senderPwd) {
        SENDER_PWD = senderPwd;
    }

    public static int getSenderServerPort() {
        return SENDER_SERVER_PORT;
    }

    private static void setSenderServerPort(int senderServerPort) {
        SENDER_SERVER_PORT = senderServerPort;
    }

    public static String getServiceKey() {
        return SERVICE_KEY;
    }

    private static void setServiceKey(String serviceKey) {
        SERVICE_KEY = serviceKey;
    }

    public static short getTriggerHour() {
        return TRIGGER_HOUR;
    }

    private static void setTriggerHour(short triggerHour) {
        TRIGGER_HOUR = triggerHour;
    }

    public static short getTriggerMin() {
        return TRIGGER_MIN;
    }

    private static void setTriggerMin(short triggerMin) {
        TRIGGER_MIN = triggerMin;
    }

    public static String getDbAddr() {
        return DB_ADDR;
    }

    private static void setDbAddr(String dbAddr) {
        DB_ADDR = dbAddr;
    }

    public static String getDbUsr() {
        return DB_USR;
    }

    private static void setDbUsr(String dbUsr) {
        DB_USR = dbUsr;
    }

    public static String getDbPwd() {
        return DB_PWD;
    }

    private static void setDbPwd(String dbPwd) {
        DB_PWD = dbPwd;
    }

    static void load(String confFile) throws IOException, ParseException {
        System.out.println("[conf-manager] Using configuration: " + confFile);
        FileReader fileReader = new FileReader(confFile);
        JSONObject jsonObject = new JSONObject(new JSONParser()
                .parse(fileReader).toString());
        DeliverJob.setLastId(jsonObject.getInt("begin_from_page_id"));
        ConfigurationManager.setServiceKey(jsonObject.getString("service_key"));
        ConfigurationManager.setMailValidationServer(jsonObject.getString("mail_validation_server"));
        JSONObject senderConfJSONObj = jsonObject.getJSONObject("sender");
        ConfigurationManager.setSenderPwd(senderConfJSONObj.getString("password"));
        ConfigurationManager.setSenderUsr(senderConfJSONObj.getString("username"));
        ConfigurationManager.setSenderServerAddr(senderConfJSONObj.getString("smtp_server_addr"));
        ConfigurationManager.setSenderServerPort(senderConfJSONObj.getInt("smtp_server_port"));
        JSONObject schedulerConfJSONObj = jsonObject.getJSONObject("scheduler");
        ConfigurationManager.setTriggerHour((short) schedulerConfJSONObj.getInt("trigger_everyday_at_hour_in_24h"));
        ConfigurationManager.setTriggerMin((short) schedulerConfJSONObj.getInt("trigger_everyday_at_minute"));
        JSONObject databaseConfJSONObj = jsonObject.getJSONObject("database");
        ConfigurationManager.setDbAddr(databaseConfJSONObj.getString("address"));
        ConfigurationManager.setDbUsr(databaseConfJSONObj.getString("username"));
        ConfigurationManager.setDbPwd(databaseConfJSONObj.getString("password"));
    }

    static void load() throws IOException, ParseException {
        load("configuration.json");
    }
}
