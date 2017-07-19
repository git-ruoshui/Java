package com.zgc.singleton;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-05.15:21
 * Connect: 1072238017@qq.com
 * 单例模式示例-饿汉式
 */
public class Singleton_Ehan {
    private Singleton_Ehan() {
    }

    //私有的构造函数
    private static Singleton_Ehan singletonInstance = new Singleton_Ehan();//

    //供外部访问的静态方法
    public static Singleton_Ehan getSingletonInstance() {
        return singletonInstance;
    }

}



