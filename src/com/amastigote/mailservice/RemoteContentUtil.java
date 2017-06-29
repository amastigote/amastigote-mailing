package com.amastigote.mailservice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteContentUtil {
    private static final String sqlForMail = "SELECT mail FROM email_registry WHERE validated='1'";
    private static final String sqlForPage = "SELECT name, url FROM item WHERE id>?";
    private static final ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("javascript");

    public static synchronized List<String> getValidatedMails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForMail);
        List<String> validatedMails = new ArrayList<>();
        while (resultSet.next())
            validatedMails.add(resultSet.getString("mail"));
        return validatedMails;
    }

    public static synchronized Map<String, String> getNewlyArchivedPages(int previousId) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, ScriptException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForPage, new ArrayList<Object>() {{
            add(previousId);
        }});
        Map<String, String> newlyArchivedPages = new HashMap<>();
        while (resultSet.next())
            newlyArchivedPages.put((String) jsEngine.eval("unescape(\"" + resultSet.getString("name") + "\")"), resultSet.getString("url"));
        return newlyArchivedPages;
    }
}
