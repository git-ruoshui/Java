package com.zgc.singleton;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-17.17:51
 * Connect: 1072238017@qq.com
 * 双重锁机制
 */
public class Singleton_doubleSync {
    private static Singleton_doubleSync instance;
    private Singleton_doubleSync (){
    }
    public static Singleton_doubleSync getInstance(){    //对获取实例的方法进行同步
        if (instance == null){
            synchronized(Singleton_doubleSync.class){
                if (instance == null)
                    instance = new Singleton_doubleSync();
            }
        }
        return instance;
    }
}
