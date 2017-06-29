package com.amastigote.mailservice;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteContentUtil {
    private static final String sqlForMail = "SELECT mail FROM email_registry WHERE validated='1'";

    public static synchronized   List<String> getValidatedMails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DataSourceCore.doQuery(sqlForMail);
        List<String> validatedMails = new ArrayList<>();
        while (resultSet.next())
            validatedMails.add(resultSet.getString("mail"));
        return validatedMails;
    }

    public static synchronized  Map<String, String> getNewlyArchivedPages() throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        ResultSet resultSet = DataSourceCore.doQuery("");
        Map<String, String> newlyArchivedPages = new HashMap<>();
        while (resultSet.next())
            newlyArchivedPages.put(URLDecoder.decode(resultSet.getString("name"), "utf-8"), resultSet.getString("url"));
        return newlyArchivedPages;
    }
}
