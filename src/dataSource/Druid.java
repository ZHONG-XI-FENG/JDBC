package dataSource;
/*
@projectName JDBC
@author Joe See Feng
@create 04/09/2021-11:41 PM
@description 
*/

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.DriverManagerDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Properties;

public class Druid {
@Test
    public void druidTest01() throws Exception {
    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\druid.properties"));

    //创建数据库连接池
    DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
    Connection connection = dataSource.getConnection();
    System.out.println("成功");
    connection.close();
}
}
