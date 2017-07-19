package com.zgc.produce_customer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Vector;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-17.18:10
 * Connect: 1072238017@qq.com
 * 生产者
 */
public class Producer implements Runnable {


    private final Vector sharedQueue;//vector可实现类似动态数组
    private final int SIZE;
    Logger logger  =  Logger.getLogger(Producer. class );
    public Producer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        PropertyConfigurator.configure( "config/log4j.properties " );
    }

    @Override
    public void run() {

        for (int i = 0; i < 7; i++) {
            System.out.println("当前生产数量:" + i);
            try {
                produce(i);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                logger.error(ex);
            }
        }


    }

    private void produce(int i) throws InterruptedException {

        //wait if queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());
                sharedQueue.wait();
            }
        }
        //producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notify();
        }
    }
}
