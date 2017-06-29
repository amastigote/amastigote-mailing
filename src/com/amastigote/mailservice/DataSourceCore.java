package com.amastigote.mailservice;

import java.sql.*;

public class DataSourceCore {
    private static String url = "jdbc:mysql://" + Main.getDbAddr() + "?useUnicode=true&characterEncoding=utf-8";
    private static String usr = Main.getDbUsr();
    private static String pwd = Main.getDbPwd();

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, usr, pwd);
    }

    public static ResultSet doQuery(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
}
