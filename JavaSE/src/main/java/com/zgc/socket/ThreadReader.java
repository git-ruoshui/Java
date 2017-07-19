package com.zgc.socket;

import com.zgc.util.Log4jUtil;
import org.apache.log4j.Logger;

import java.io.*;


/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.13:42
 * Connect: 1072238017@qq.com
 * socket读取输入流线程
 */
public class ThreadReader implements Runnable {
    private InputStream inputStream;
    Logger logger;
    byte[] b;
    int length;//读取字符长度
    String message;

    public ThreadReader(InputStream inputStream) {
        Log4jUtil.loadLog4j();
        logger=Logger.getLogger(ThreadReader.class);
        this.inputStream = inputStream;
    }

    @Override
    public void run() {

        try {
            while(true){
                b = new byte[1024];
                length = inputStream.read(b);//接收客户端消息
                if(0!=length){
                    message = new String(b,0,length);
                }
                System.out.println(Thread.currentThread().getName()+":"+message);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(null,e);
        }

    }
}
