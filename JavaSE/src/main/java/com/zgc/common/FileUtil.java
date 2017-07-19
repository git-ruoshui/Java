package com.zgc.common;

import java.io.*;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-12.15:17
 * Connect: 1072238017@qq.com
 */
public class FileUtil {
    public static void main(String[] args) {
        readTxtFile("C:\\Users\\ruoshui\\Desktop\\test.txt");
        //writeTxtFile("C:\\Users\\ruoshui\\Desktop\\test2.txt", "我在测试");
    }

    /**
     * 读取文本文件
     *
     * @param path
     */
    public static void readTxtFile(String path) {

        String encoding = "UTF-8";
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader;
        try {
            long starttime = System.currentTimeMillis();
            File file = new File(path);//获得文件句柄
            if (file.isFile() && file.exists()) {
                inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
                bufferedReader = new BufferedReader(inputStreamReader);
                String lineTxt;
                int i = 1;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(i + "---" + lineTxt);
                    i++;
                }
                inputStreamReader.close();
                long endtime = System.currentTimeMillis();
                System.out.println(endtime - starttime + "ms");

            } else {
                System.out.println("找不到指定文件");
            }
        } catch (IOException e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 向文本写入内容
     *
     * @param filepath 文件目录
     * @param newstr   写入的内容
     */
    public static void writeTxtFile(String filepath, String newstr) {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp ;

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //return bool;
    }
}
