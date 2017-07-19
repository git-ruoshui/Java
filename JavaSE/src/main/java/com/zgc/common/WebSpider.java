package com.zgc.common;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-07.08:43
 * Connect: 1072238017@qq.com
 * 网络爬虫demo
 */
public class WebSpider {
    public static void main(String[] args) {
        URL url;
        File directory = new File(".");
        URLConnection urlConnection;
        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;
        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);//正则表达式的编译表示
        String path=null;
        try {
            path= directory.getCanonicalPath()+"\\url.text";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            url = new URL("http://www.qq.com/");
            urlConnection = url.openConnection();
            printWriter = new PrintWriter(new FileWriter(path, true));//这里我们把收集到的链接存储当前项目下
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buf = null;
            while ((buf = bufferedReader.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    printWriter.println(buf_m.group());
                }
            }
            System.out.println("获取成功！");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("连接超时，请检查连接！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.close();
        }
    }
}

