package dao.dao;
/*
@projectName JDBC
@author Joe See Feng
@create 05/09/2021-4:13 PM
@description 
*/

import dataSource.JDBCUtilsByDruid;
import netscape.javascript.JSException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class BasicDao<T> {

    private QueryRunner qr = new QueryRunner();

    //

    public int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int affectedRows = qr.update(connection, sql, params);
            return affectedRows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    public List<T> queryMulti(String sql, Class<T> tClass, Object... params) {
        //Class<T>用于底层反射获取类里面的属性
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> query = qr.query(connection, sql, new BeanListHandler<>(tClass), params);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    public T querySingle(String sql, Class<T> tClass, Object... params) {
        //Class<T>用于底层反射获取类里面的属性
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T query = qr.query(connection, sql, new BeanHandler<>(tClass), params);

            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    public Object queryScalar(String sql, Object... params) {
        //Class<T>用于底层反射获取类里面的属性
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Object obj = qr.query(connection, sql, new ScalarHandler<>(), params);

            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }
}
