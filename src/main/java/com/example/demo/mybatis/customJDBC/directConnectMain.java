package com.example.demo.mybatis.customJDBC;

import com.example.demo.mybatis.entity.User;

import java.sql.*;

/**
 * 原始JDBC连接
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/1
 * @time 17:16
 */
public class directConnectMain {

    public static void main(String[] args) {
        // 数据库连接信息：地址、账号、密码
        Connection connection = null;
        // 获取预处理statement
        PreparedStatement preparedStatement = null;
        // 向数据库发出sql执行查询，查询出结果集
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC&characterEncoding = utf-8", "maodou", "Zyd980310");
            // 定义sql语句？表示占位符
            String sql = "select * from user where name = ?";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1, "李四");
            // 向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            // 遍历查询结果集
            User user = new User();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String username = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                // 封装User
                user.setId(id);
                user.setName(username);
                user.setGender(gender);
                System.out.println(user.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
