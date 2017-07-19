package com.zgc.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.13:44
 * Connect: 1072238017@qq.com
 * socket发送数据线程
 */
public class ThreadWriter implements Runnable {
    private OutputStream outputStream;

    public ThreadWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while(true){
               // System.out.println("客户端："+Thread.currentThread().getName());
                String message = sc.next();
                outputStream.write(message.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
