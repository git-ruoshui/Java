package com.zgc.socket;

import com.zgc.util.Log4jUtil;
import org.apache.log4j.Logger;

import java.net.Socket;
import java.util.Scanner;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-05.15:57
 * Connect: 1072238017@qq.com
 */
public class SocketClient {
    private int port = 9000;
    private String ip = "127.0.0.1";
    private  Socket socket;
    private String clientName;
    Logger logger;

    public SocketClient() {
        Log4jUtil.loadLog4j();
        logger = Logger.getLogger(SocketClient.class);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {

        System.out.println("-----客户端已开启-----");
        System.out.println("请输入客户端名字：");
        Scanner sc = new Scanner(System.in);
        clientName = sc.next();
        socket = new Socket(ip, port);
        hands();

    }

    public void hands() throws Exception {
//        Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
//        threadWriter.setName(clientName);
//        Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()), Thread.currentThread().getName());
//
//        threadReader.start();
//        threadWriter.start();
        Thread thread=new Thread(new Client(socket));
        thread.setName(clientName);
        thread.start();

    }

    public static void main(String[] args) {

        new SocketClient();

    }
}
