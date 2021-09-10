package utils;
/*
@projectName JDBC
@author Joe See Feng
@create 03/09/2021-10:15 PM
@description 
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            //将编译异常转成运行异常，使调用者可以选择捕获该异常或默认处理
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void close(ResultSet set, Statement statement, Connection connection) {
        /**
         * @Description: 如果需要关闭就传入参数，否则传NULL.
         * @Author: Joe C Von
         * @Create:10:24 PM 03/09/2021
         * @param set
         * @param statement
         * @param connection
         * @Return void
         * @Throws
         */

        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
