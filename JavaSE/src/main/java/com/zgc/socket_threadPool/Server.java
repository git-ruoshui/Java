package com.zgc.socket_threadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-19.17:05
 * Connect: 1072238017@qq.com
 */
public class Server {
    private ServerSocket serverSocket;
    //所有客户端输出流
    private List<PrintWriter> allOut;
    //线程池
    private ExecutorService threadPool;

    public Server() {
        try {
            serverSocket = new ServerSocket(8088);
            allOut = new ArrayList<>();
            threadPool = Executors.newFixedThreadPool(40);//线程池数量
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 将输出流存入共享集合，与下面两个方法互斥，保证同步安全
     */
    private synchronized void addOut(PrintWriter out) {
        allOut.add(out);
    }

    private synchronized void removeOut(PrintWriter out) {
        allOut.remove(out);
    }

    private synchronized void sendMessage(String message) {
        for (PrintWriter printWriter : allOut) {
            printWriter.println(message);
        }
    }

    /*
     * 线程体：用于并发处理不同客户端的交互
     *
     */
    class ClientHandler implements Runnable{

        private Socket socket;

        //构造函数设置为public
        public ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            try {
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);
                //存入共享集合
                //allOut.add(pw);
                addOut(pw);

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String message ;
                while((message = br.readLine()) != null){
                    //for(PrintWriter o: allOut){
                    //    o.println(message);
                    sendMessage(message);
                }
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                //当客户端断线时，要将输出流从集合中删除
                //allOut.remove(pw);
                removeOut(pw);
                if(socket != null){
                    try{
                        socket.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void start(){
        try{
            //循环监听客户端的连接
            while(true){

                System.out.println("等待客户端连接。。。");
                Socket socket = serverSocket.accept();
                System.out.println("客户端已连接！");

                ClientHandler handler = new ClientHandler(socket);
                //启动一个线程来完成针对该客户端的交互
                threadPool.execute(handler);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Server server = new Server();
        server.start();
    }
}
