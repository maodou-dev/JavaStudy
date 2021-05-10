package com.example.demo.myTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/4/7
 * @time 16:30
 */
public class Emp {

    private static volatile Integer empId = 0;
    private static volatile Integer opterId = 0;
    private static volatile Integer uact = 0;
    private static volatile Integer auth = 0;
    public static void main(String[] args) {
        // 声明Connection对象
        Connection con;
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名 test
        String url = "jdbc:mysql://rm-a1am051356az74fkd.mysql.rds.ops.public-gzybcloud.com:3306/gzpubserv_db?connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        // MySQL配置时的用户名
        String user = "yhtest";
        // MySQL配置时的密码
        String password = "Yinhai123";
        // 遍历查询结果集
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("\n\t\t成功以 " + user + " 身份连接到数据库！！！");
            }
            // 2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            // 要执行的SQL语句
            String sql1 = "select * from user_emp_b";
            String sql2 = "select * from user_emp_b_test";
            String sql3 = "insert into emp_opter_b_test";


            List list = new ArrayList();
            List list1 = new ArrayList();
            List list2 = new ArrayList();
            EmpOpterDO empOpterDO = new EmpOpterDO();
            EmpNthlUactDO empNthlUactDO = new EmpNthlUactDO();
            for (int i = 0; i < list.size(); i++) {
                empOpterDO = saveEmpOpterDO(empOpterDO);
                ResultSet resultSet = statement.executeQuery(sql1);
                empNthlUactDO = registerUnit(empNthlUactDO, empOpterDO);
                ResultSet resultSet2 = statement.executeQuery(sql2);

            }

            // 3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql1);
            System.out.println("\n\t\t执行结果如下所示:");
            System.out.println("\t\t-----------------------------------------------------------------");
            System.out.println("\t\t|\t" + "EMP_ID" + "\t" + "EMP_NAME" + "\t" + "USCC" + "\t" + "LEGREP_TEL" + "\t" + "EMP_ADDR" + "\t\t" + "LEGREP_NAME\t|");
            System.out.println("\t\t-----------------------------------------------------------------");

            String EMP_ID = null;
            String EMP_NAME = null;
            String USCC = null;
            String LEGREP_TEL = null;
            String EMP_ADDR = null;
            String LEGREP_NAME = null;

            while (rs.next()) {
                // 获取 ID 这列数据
                EMP_ID = rs.getString("EMP_ID");
                // 获取 Name 这列数据
                EMP_NAME = rs.getString("EMP_NAME");
                // 获取 Sex 这列数据
                USCC = rs.getString("USCC");
                // 获取 Age 这列数据
                LEGREP_TEL = rs.getString("LEGREP_TEL");
                // 获取 Phone 这列数据
                EMP_ADDR = rs.getString("EMP_ADDR");
                // 获取 Address 这列数据
                LEGREP_NAME = rs.getString("LEGREP_NAME");
                // 输出结果
                System.out.println("\t\t|\t" + EMP_ID + "\t" + EMP_NAME + "\t" + USCC + "\t" + LEGREP_TEL + "\t" + EMP_ADDR + "\t" + LEGREP_NAME + "\t|\t\t");
            }
            System.out.println("\t\t-----------------------------------------------------------------");
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            // 数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("\t\t\t\t\t\t\t\t获取数据库数据完毕！!！");
        }
    }

    public static Object saveUserEmpDO(UserEmpDO userEmpDO){
        String userEmpId = String.valueOf(empId);
        return null;
    }

    public static Object registerAgent(){
        return null;
    }
    public static EmpNthlUactDO registerUnit(EmpNthlUactDO empNthlUactDO, EmpOpterDO empOpterDO){
        String empUactAgentId = String.valueOf(++uact);
        Date nowDate = new Date();
        empNthlUactDO.setRid(empUactAgentId);
        empNthlUactDO.setUactSoucId(empOpterDO.getEmpOpterId());
        empNthlUactDO.setEmpNthlUactId(empUactAgentId);
        empNthlUactDO.setEmpNthlUact(empOpterDO.getOpterTel());

        empNthlUactDO.setPwd("8fb420cf8f7228891aefc94c989dc325");
        empNthlUactDO.setSalt("rVnW1KwvJ");
        empNthlUactDO.setCrterName(empOpterDO.getEmpOpterName());
        empNthlUactDO.setCrteTime(nowDate);
        empNthlUactDO.setOpterName(empOpterDO.getEmpOpterName());
        empNthlUactDO.setOptTime(nowDate);
        empNthlUactDO.setRegChnl("0");
        empNthlUactDO.setUactStas("0");
        empNthlUactDO.setUactType("3");
        empNthlUactDO.setUpdtTime(nowDate);
        return empNthlUactDO;
    }
    public static Object saveEmpAuthOpterB(){
        return null;
    }
    public static EmpOpterDO saveEmpOpterDO(EmpOpterDO empOpterDO){
        String empOpterId = String.valueOf(++opterId);
        Date nowDate = new Date();
        empOpterDO.setRid(empOpterId);
        empOpterDO.setEmpOpterId(empOpterId);
//        empOpterDO.setEmpOpterName(empOpterName);
//        empOpterDO.setOpterTel(opterTel);
//        empOpterDO.setCertno(certno);
//        empOpterDO.setCertValiBegnTime(certValiBegnTime);
//        empOpterDO.setCertValiEndTime(certValiEndTime);
//        empOpterDO.setPsnCertType(certType);
        empOpterDO.setCrterName(empOpterDO.getOpterName());
        empOpterDO.setCrterTime(nowDate);
        empOpterDO.setOpterName(empOpterDO.getOpterName());
        empOpterDO.setOptTime(nowDate);
        empOpterDO.setUpdtTime(nowDate);

        return empOpterDO;
    }
}
