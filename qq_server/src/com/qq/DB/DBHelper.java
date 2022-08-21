package com.qq.DB;


import com.qq.config.DBConfig;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: HomeWork
 * @description:
 * @author: Mr.Wang
 * @create: 2021-08-11 20:27
 **/
public class DBHelper {

    static {
        try {
            Class.forName(DBConfig.getDriverClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBConfig.getUrl(), DBConfig.getUsername(), DBConfig.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    //关闭资源
    public static void closeAll(Connection con, PreparedStatement statement, ResultSet set) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (set != null) {
            try {
                set.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //通用增删改
    public static boolean update(Connection connection, String sql, Object... args) throws Exception {
        PreparedStatement ps = null;
        ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        int i = ps.executeUpdate();
        if (i > 0) {
            return true;
        }
        DBHelper.closeAll(null, ps, null);
        return false;
    }

    //通用查询一个bean
    public static  <T> T selectForBean(Connection connection,Class<T> clazz,String sql,Object... args) throws Exception{
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (rs.next()){
            T t=clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object value = rs.getObject(i + 1);
                String columnLabel = metaData.getColumnLabel(i + 1);
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t,value);
            }
            return t;
        }
        return null;
    }

    //通用查询一个list
    public static <T> List<T> selectForList(Connection connection, Class<T> clazz, String sql, Object... args) throws Exception{
        List<T> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()){
            T t=clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object value = rs.getObject(i + 1);
                String columnLabel = metaData.getColumnLabel(i + 1);
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t,value);
            }
            list.add(t);
        }
        if (list.size()!=0){
            return list;
        }
        return null;
    }

    //通用查询一个特殊值
    public static  <E> E getValue(Connection connection,String sql,Object ...args) throws  Exception{
            PreparedStatement ps = connection.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        return null;
    }
}
