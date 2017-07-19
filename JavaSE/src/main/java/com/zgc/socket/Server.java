package com.zgc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/**
 * 暂时存在多个客户端发送时，server回复时，谁先回答消息就给谁
 *
 */

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.17:36
 * Connect: 1072238017@qq.com
 */
public class Server implements Runnable {
   private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public  void run() {
        try {
            // 读取客户端传过来信息的DataInputStream
            DataInputStream in = new DataInputStream(socket.getInputStream());
            // 向客户端发送信息的DataOutputStream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            // 获取控制台输入的Scanner
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // 读取来自客户端的信息
                String accpet = in.readUTF();
                System.out.println(accpet);
                String send = scanner.nextLine();
                System.out.println("服务器：" + send);
                // 把服务器端的输入发给客户端
                out.writeUTF("服务器：" + send);
            }
        }
     catch (IOException e) {
        e.printStackTrace();
    }
        finally {// 建立连接失败的话不会执行socket.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}
