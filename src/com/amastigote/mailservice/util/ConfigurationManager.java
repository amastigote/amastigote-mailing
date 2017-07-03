package com.amastigote.mailservice.util;

import com.amastigote.mailservice.delivery.DeliverJob;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    public static void loadConfiguration(String confFile) throws IOException, ParseException {
        System.out.println("[amastigote] Using configuration: " + confFile);
        FileReader fileReader = new FileReader(confFile);
        JSONObject jsonObject = new JSONObject(new JSONParser()
                .parse(fileReader).toString());
        DeliverJob.setLastId(jsonObject.getInt("begin_from_page_id"));
        Configuration.setServiceKey(jsonObject.getString("service_key"));
        JSONObject senderConfJSONObj = jsonObject.getJSONObject("sender");
        Configuration.setSenderPwd(senderConfJSONObj.getString("password"));
        Configuration.setSenderUsr(senderConfJSONObj.getString("username"));
        Configuration.setSenderServerAddr(senderConfJSONObj.getString("smtp_server_addr"));
        Configuration.setSenderServerPort(senderConfJSONObj.getInt("smtp_server_port"));
        JSONObject schedulerConfJSONObj = jsonObject.getJSONObject("scheduler");
        Configuration.setTriggerHour((short) schedulerConfJSONObj.getInt("trigger_everyday_at_hour_in_24h"));
        Configuration.setTriggerMin((short) schedulerConfJSONObj.getInt("trigger_everyday_at_minute"));
        JSONObject databaseConfJSONObj = jsonObject.getJSONObject("database");
        Configuration.setDbAddr(databaseConfJSONObj.getString("address"));
        Configuration.setDbUsr(databaseConfJSONObj.getString("username"));
        Configuration.setDbPwd(databaseConfJSONObj.getString("password"));
    }

    public static void loadConfiguration() throws IOException, ParseException {
        loadConfiguration("configuration.json");
    }
}
