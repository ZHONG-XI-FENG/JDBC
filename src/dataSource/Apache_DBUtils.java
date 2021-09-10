package dataSource;
/*
@projectName JDBC
@author Joe See Feng
@create 05/09/2021-2:59 PM
@description 
*/

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.List;

public class Apache_DBUtils {
    @Test
    public void testQueryMany() throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student where id >=?";
        //query 执行sql语句得到resultset， 将resultset封装到arraylist中
        //BeanListHandler<>(Student.class) 底层用反射知道student有哪些属性
        //1 表示传值给问号，可写多个
        List<Student> list = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class), 1);

        for(Student student:list){
            System.out.println(student);
        }
        //resultset在底层已经关闭,preparedStatement(底层创建的)也已经关闭
        JDBCUtilsByDruid.closeConnection(null,null,connection);
    }
    @Test
    public void testQuerySingle()throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student where id =?";
        //单个对象用BeanHandler,多个用BeanListHandler
        Student s = queryRunner.query(connection, sql, new BeanHandler<>(Student.class), 34);
        System.out.println(s);
        JDBCUtilsByDruid.closeConnection(null,null,connection);

    }

    @Test
    public void testQuerySingleObject()throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from student where id =?";
        //返回一个对象
        Object obj = queryRunner.query(connection, sql, new ScalarHandler<>(), 3);
        System.out.println(obj);
        JDBCUtilsByDruid.closeConnection(null,null,connection);

    }

    @Test
    public void testDML()throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        //String sql = "update student set name = ? where id = ?";
        String sql = "insert into student values(null,'Ham','男',45)";
        //update() ’增删改‘都可以
        int affectedRows = queryRunner.update(connection,sql);
        System.out.println(affectedRows);
        JDBCUtilsByDruid.closeConnection(null,null,connection);

    }
}
