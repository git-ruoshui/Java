package com.zgc.thread;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-05.16:27
 * Connect: 1072238017@qq.com
 * 4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1
 */
public class ThreadStudy {
    private int j;

    public void testThread() {
        ThreadStudy tt =new ThreadStudy();
        Inc inc=tt.new Inc();
        Dec dec=tt.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread t=new Thread(inc);
            t.start();
            t=new Thread(dec);
            t.start();
        }
    }
    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }

    class  Inc implements Runnable{
        public void run(){
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }
    class  Dec implements Runnable{
        public void run(){
            for (int i = 0; i <100 ; i++) {
                dec();
            }
        }
    }
}
