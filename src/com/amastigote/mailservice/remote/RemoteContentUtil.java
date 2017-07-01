package com.amastigote.mailservice.remote;

import com.amastigote.mailservice.Main;
import com.amastigote.mailservice.util.Page;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RemoteContentUtil {
    private static final String sqlForMail = "SELECT mail FROM email_registry WHERE validated='1'";
    private static final String sqlForPage = "SELECT name, url, id FROM item WHERE id>?";
    private static final String sqlForTag = "SELECT name FROM item_tag INNER JOIN tag ON tag_id=id WHERE item_id=?";
    private static final ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("javascript");

    public static synchronized List<String> getValidatedMails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForMail);
        List<String> validatedMails = new ArrayList<>();
        while (resultSet.next())
            validatedMails.add(resultSet.getString("mail"));
        return validatedMails;
    }

    public static synchronized List<Page> getNewlyArchivedPages(int previousId) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, ScriptException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForPage, new ArrayList<Object>() {{
            add(previousId);
        }});
        List<Page> pages = new ArrayList<>();
        int lastId = Main.getLastId();
        while (resultSet.next()) {
            lastId = resultSet.getInt("id");
            pages.add(new Page()
                    .setTitle((String) jsEngine.eval("unescape(\"" + resultSet.getString("name") + "\")"))
                    .setUrl(resultSet.getString("url"))
                    .setTags(getTags(lastId))
            );
        }
        Main.setLastId(lastId);
        return pages;
    }

    private static List<String> getTags(int itemId) throws SQLException, ClassNotFoundException, ScriptException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForTag, new ArrayList<Object>() {{
            add(itemId);
        }});
        List<String> tags = new ArrayList<>();
        while (resultSet.next())
            tags.add((String) jsEngine.eval("unescape(\"" + resultSet.getString("name") + "\")"));
        return tags;
    }
}
