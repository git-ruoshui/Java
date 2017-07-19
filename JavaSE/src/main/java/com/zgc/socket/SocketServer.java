package com.zgc.socket;

import com.zgc.util.Log4jUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-05.15:41
 * Connect: 1072238017@qq.com
 */
public class SocketServer {
    private int port = 9000;//端口号
    private ServerSocket server;//声明服务器
    private static Socket socket;//声明客户端
    private String serverName;
    Logger logger;//同一个包可以访问

    public SocketServer() {
        Log4jUtil.loadLog4j();
        logger = Logger.getLogger(SocketServer.class);
        init();
    }

    /*
     * 创建服务器，开始监听
     */
    private void init() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.fatal(null, e);
        }
        System.out.println("------服务器已开启--------");
        System.out.println("请输入服务器名字：");
        Scanner sc = new Scanner(System.in);
        serverName = sc.next();
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e) {
                logger.fatal(null, e);
            }
            hands(socket);
        }


    }

    private void hands(Socket socket) {
        String key = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        System.out.println("监听到的客户端：" + key);
        Thread thread;
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //thread=new Thread(new ThreadSocket(socket));
        //thread.setName(serverName);//应该显示客户端线程
        thread=new Thread(new Server(socket));
        thread.start();
    }

    public static void main(String[] args) {
        new SocketServer();
    }
}

