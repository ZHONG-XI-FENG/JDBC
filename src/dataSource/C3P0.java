package dataSource;
/*
@projectName JDBC
@author Joe See Feng
@create 04/09/2021-11:04 PM
@description 
*/

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;


import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0 {
    @Test
    public void testC3P0_1() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        Properties properties = new Properties();
        try {
            //获取所需参数
            properties.load(new FileInputStream("src\\mysql.properties"));
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");

            try {
                //设置连接池参数
                comboPooledDataSource.setDriverClass(driver);
                comboPooledDataSource.setJdbcUrl(url);
                comboPooledDataSource.setUser(user);
                comboPooledDataSource.setPassword(password);
                comboPooledDataSource.setInitialPoolSize(10);
                comboPooledDataSource.setMaxPoolSize(50);

                try {
                    Connection connection = comboPooledDataSource.getConnection();
                    System.out.println("成功");
                    connection.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void testC3P0_2() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("zhongxi");
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("成功");
        connection.close();
    }
}
