package zhongxi.PreparedStatement;
/*
@projectName JDBC
@author Joe See Feng
@create 05/09/2021-12:13 AM
@description 
*/

import com.alibaba.druid.pool.DruidDataSourceFactory;
import dataSource.JDBCUtilsByDruid;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class TestDruid {
    public static void main(String[] args) throws Exception {

        Connection connection = JDBCUtilsByDruid.getConnection();

        String sql = "insert into student values(null,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "AXY");
        preparedStatement.setString(2, "女");
        preparedStatement.setInt(3, 23);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0 ? "成功" : "失败");
        //JDBCUtilsByDruid.closeConnection(null, preparedStatement, connection);

        sql = "select * from student";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String gender = resultSet.getString(3);
            int age = resultSet.getInt(4);
            System.out.println(id + " " + name + " " + gender + " " + age);
        }

        JDBCUtilsByDruid.closeConnection(resultSet,preparedStatement,connection);

        //System.out.println(connection);
    }

}
