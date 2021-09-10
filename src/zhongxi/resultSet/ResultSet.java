package zhongxi.resultSet;
/*
@projectName JDBC
@author Joe See Feng
@create 03/09/2021-3:57 PM
@description 
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;

public class ResultSet {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        String sql = "select * from student";

        java.sql.ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt(1);//获取改行的第一列
            String name = resultSet.getString(2);
            String gender = resultSet.getString(3);
            int age = resultSet.getInt(4);
            System.out.println(id+" "+name+" "+gender+" "+age);
        }
        resultSet.close();
        statement.close();
        connection.close();


        //System.out.println(connection);
    }

}
