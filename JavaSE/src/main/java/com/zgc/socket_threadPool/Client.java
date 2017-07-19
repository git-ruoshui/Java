package com.zgc.socket_threadPool;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.17:08
 * Connect: 1072238017@qq.com
 */
public class Client {
    private Socket socket;

    public Client(){
        try {
            socket = new Socket("localhost", 8088);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//非静态内部类，若有static是静态内部类（嵌套类）
    private class ServerHandler implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try{
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                while(true){
                    System.out.println(br.readLine());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void start(){

        try{
            ServerHandler handler = new ServerHandler();
            Thread t = new Thread(handler);
            t.setDaemon(true);
            t.start();

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);
            //创建Scanner读取用户输入内容
            Scanner scanner = new Scanner(System.in);
            while(true){
                pw.println(scanner.nextLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(socket != null){
                try{
                    socket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client client = new Client();
        client.start();
    }
}
