package com.amastigote.mailservice.util;

public class Configuration {
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

    public static String getSenderServerAddr() {
        return SENDER_SERVER_ADDR;
    }

    static void setSenderServerAddr(String senderServerAddr) {
        SENDER_SERVER_ADDR = senderServerAddr;
    }

    public static String getSenderUsr() {
        return SENDER_USR;
    }

    static void setSenderUsr(String senderUsr) {
        SENDER_USR = senderUsr;
    }

    public static String getSenderPwd() {
        return SENDER_PWD;
    }

    static void setSenderPwd(String senderPwd) {
        SENDER_PWD = senderPwd;
    }

    public static int getSenderServerPort() {
        return SENDER_SERVER_PORT;
    }

    static void setSenderServerPort(int senderServerPort) {
        SENDER_SERVER_PORT = senderServerPort;
    }

    public static String getServiceKey() {
        return SERVICE_KEY;
    }

    static void setServiceKey(String serviceKey) {
        SERVICE_KEY = serviceKey;
    }

    static short getTriggerHour() {
        return TRIGGER_HOUR;
    }

    static void setTriggerHour(short triggerHour) {
        TRIGGER_HOUR = triggerHour;
    }

    static short getTriggerMin() {
        return TRIGGER_MIN;
    }

    static void setTriggerMin(short triggerMin) {
        TRIGGER_MIN = triggerMin;
    }

    public static String getDbAddr() {
        return DB_ADDR;
    }

    static void setDbAddr(String dbAddr) {
        DB_ADDR = dbAddr;
    }

    public static String getDbUsr() {
        return DB_USR;
    }

    static void setDbUsr(String dbUsr) {
        DB_USR = dbUsr;
    }

    public static String getDbPwd() {
        return DB_PWD;
    }

    static void setDbPwd(String dbPwd) {
        DB_PWD = dbPwd;
    }
}
