package com.zgc.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Random;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-17.08:43
 * Connect: 1072238017@qq.com
 */
public class DbStoreHelper {


    public static void main(String[] args) {
        //testInsertTosql_commit();
        testInsertTosql_batch();
    }

    // 将文本文件批量插入mysql
    public void storeToDb(String srcFile) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "utf-8"));
        try {
            doStore(bfr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bfr.close();
        }
    }

    private void doStore(BufferedReader bfr) throws ClassNotFoundException, SQLException, IOException {
        //
        String connectStr = "jdbc:mysql://localhost:3306/db_ip";
        connectStr += "?useServerPrepStmts=false&rewriteBatchedStatements=true";
        String username = "zgc";
        String password = "zgc";
        String insert_sql = "INSERT INTO tb_ipinfos (iplong1,iplong2,ipstr1,ipstr2,ipdesc) VALUES (?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connectStr, username, password);
        conn.setAutoCommit(false); // 设置手动提交
        int count = 0;
        PreparedStatement psts = conn.prepareStatement(insert_sql);
        String line = null;
        while (null != (line = bfr.readLine())) {
            String[] infos = line.split(";");
            if (infos.length < 5) continue;
            psts.setLong(1, Long.valueOf(infos[0]));
            psts.setLong(2, Long.valueOf(infos[1]));
            psts.setString(3, infos[2]);
            psts.setString(4, infos[3]);
            psts.setString(5, infos[4]);
            psts.addBatch();          // 加入批量处理
            count++;
        }
        psts.executeBatch(); // 执行批量处理
        conn.commit();  // 提交
        System.out.println("All down : " + count);
        conn.close();
    }

    /**
     * 利用事务插入数据，10w大概17s左右
     */
    private static void testInsertTosql_commit() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rt = null;
        String username = "zgc";
        String password = "zgc";

        String url = "jdbc:mysql://localhost:3306/db_test?characterEncoding=UTF-8";
        url+="&useServerPrepStmts=true";//启用预编译功能，时间减少了一半
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO userinfo(uid,uname,uphone,uaddress) VALUES(?,CONCAT('姓名',?),?,?)";
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);//设置手动提交
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a, b, c, d;
            for (int i = 1; i <= 100000; i++) {
                pstm.setInt(1, i);
                pstm.setInt(2, i);
                a = rand.nextInt(10);
                b = rand.nextInt(10);
                c = rand.nextInt(10);
                d = rand.nextInt(10);
                pstm.setString(3, "188" + a + "88" + b + c + "66" + d);
                pstm.setString(4, "xxxxxxx_" + "188" + a + "88" + b + c + "66" + d);
                pstm.executeUpdate();
            }
            conn.commit();//事务提交
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime) / 1000.0 + "s");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 批量插入测试 10w 4s左右
     */
    private static void testInsertTosql_batch() {
        String url = "jdbc:mysql://localhost:3306/db_test?characterEncoding=UTF-8&rewriteBatchedStatements=true";
       // url+="&useServerPrepStmts=true";
        String user = "zgc";
        String password = "zgc";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO userinfo(uid,uname,uphone,uaddress) VALUES(?,CONCAT('姓名',?),?,?)";
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a, b, c, d;
            for (int i = 1; i <= 100000; i++) {
                pstm.setInt(1, i);
                pstm.setInt(2, i);
                a = rand.nextInt(10);
                b = rand.nextInt(10);
                c = rand.nextInt(10);
                d = rand.nextInt(10);
                pstm.setString(3, "188" + a + "88" + b + c + "66" + d);
                pstm.setString(4, "xxxxx_" + "188" + a + "88" + b + c + "66" + d);
                pstm.addBatch();
            }
            pstm.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime)/1000.0+"s");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
