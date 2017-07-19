package com.zgc.produce_customer;

import java.util.Vector;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-18.09:45
 * Connect: 1072238017@qq.com
 */
public class Producer_ConsumerTest {
    public static void main(String[] args) {
        Vector sharedQueue = new Vector();
        int size = 4;
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
        prodThread.start();
        consThread.start();
    }
}
