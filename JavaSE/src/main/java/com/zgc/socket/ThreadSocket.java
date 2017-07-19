package com.zgc.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.13:40
 * Connect: 1072238017@qq.com
 * 服务器处理socket线程
 */
public class ThreadSocket implements Runnable {
    private Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Thread threadReader = new Thread(new ThreadReader(socket.getInputStream()));
            Thread threadWriter = new Thread(new ThreadWriter(socket.getOutputStream()));
            threadReader.start();
            threadWriter.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
