package com.amastigote.mailservice.service.remote;

import com.amastigote.mailservice.Configuration;

import java.sql.*;
import java.util.List;

class DataSourceCore {
    private static String url = "jdbc:mysql://" + Configuration.getDbAddr() + "?useUnicode=true&characterEncoding=utf-8";
    private static String usr = Configuration.getDbUsr();
    private static String pwd = Configuration.getDbPwd();

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, usr, pwd);
    }

    static ResultSet doQuery(String sql) throws SQLException, ClassNotFoundException {
        return doQuery(sql, null);
    }

    static ResultSet doQuery(String sql, List<Object> params) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        if (params != null)
            for (int i = 0; i < params.size(); i++)
                preparedStatement.setObject(i + 1, params.get(i));
        return preparedStatement.executeQuery();
    }
}
