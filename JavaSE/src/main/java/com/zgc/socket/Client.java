package com.zgc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.17:40
 * Connect: 1072238017@qq.com
 */
public class Client implements Runnable {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public  void run() {
        try {
            // 读取服务器端传过来信息的DataInputStream
            DataInputStream in = new DataInputStream(socket.getInputStream());
            // 向服务器端发送信息的DataOutputStream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // 装饰标准输入流，用于从控制台输入
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String send = scanner.nextLine();
                System.out.println("客户端：" + send);
                // 把从控制台得到的信息传送给服务器
                out.writeUTF(Thread.currentThread().getName()+"-客户端：" + send);
                // 读取来自服务器的信息
                String accpet = in.readUTF();
                System.out.println(accpet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
