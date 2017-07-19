package com.zgc.util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-17.18:31
 * Connect: 1072238017@qq.com
 */
public class Log4jUtil {
    public static void main(String[] args) {
        System.out.println(Log4jUtil.class.getResource("/").getPath());

        System.setProperty ("WORKDIR", System.getProperty("user.dir"));
        PropertyConfigurator.configure( "config/log4j.properties" );
        Logger logger  =  Logger.getLogger(Log4jUtil. class );
        try{
            System.out.println(5/0);

        }
        catch (Exception E){
            logger.error(null,E);
        }
    }
    public static void loadLog4j(){
        System.setProperty ("WORKDIR", System.getProperty("user.dir"));
        PropertyConfigurator.configure( "config/log4j.properties" );
    }
}
