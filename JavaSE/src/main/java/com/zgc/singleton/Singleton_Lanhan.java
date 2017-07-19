package com.zgc.singleton;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-17.17:40
 * Connect: 1072238017@qq.com
 * 使用同步锁单例模式--懒汉式
 */
public class Singleton_Lanhan {
    private static Singleton_Lanhan singleton=null;

    private Singleton_Lanhan() {

    }

    public static synchronized Singleton_Lanhan getSingleton() {
        if (null == singleton) {
            singleton = new Singleton_Lanhan();
        }
        return singleton;
    }

}
