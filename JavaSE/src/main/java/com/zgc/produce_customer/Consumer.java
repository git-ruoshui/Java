package com.zgc.produce_customer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Vector;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-18.09:24
 * Connect: 1072238017@qq.com
 * 消费者
 */
public class Consumer implements Runnable {
    private final Vector sharedQueue;//vector可实现类似动态数组
    private final int SIZE;
    Logger logger  =  Logger.getLogger(Consumer. class );
    public Consumer(Vector sharedQueue, int size){
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        PropertyConfigurator.configure( "config/log4j.properties " );
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("已消费数量： " + consume());
                Thread.sleep(50);
            } catch (InterruptedException ex) {
               logger.error(ex);
            }
        }
    }
    private int consume() throws InterruptedException {

        //wait if queue is empty
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                System.out.println("Queue is empty " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());
                sharedQueue.wait();
            }
        }

        //otherwise consume element and notify waiting producer
        synchronized (sharedQueue) {
            sharedQueue.notify();
            return (Integer) sharedQueue.remove(0);
        }
    }
}
