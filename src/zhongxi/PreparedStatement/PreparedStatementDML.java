package zhongxi.PreparedStatement;
/*
@projectName JDBC
@author Joe See Feng
@create 03/09/2021-9:48 PM
@description 
*/

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PreparedStatementDML {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "insert into student values(null,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "FZX");
        preparedStatement.setString(2, "男");
        preparedStatement.setInt(3, 23);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"成功":"失败");
        preparedStatement.close();
        connection.close();


        //System.out.println(connection);
    }
}
