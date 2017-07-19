package com.zgc.common;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-07.15:04
 * Connect: 1072238017@qq.com
 * 求最大公约数--欧几里得算法
 */
public class FindDivisor {
    public static void main(String[] args) {
     long l= findDivisor(234,345);
        System.out.println(l);
        //testMe(5);
        System.out.println(testMe(5));
    }
    /**
     * 查找最大公约数
     * @param m
     * @param n
     */
    private static long findDivisor(long m,long n) {
        long rem;
        while (n!=0){
            rem=m%n;
            m=n;
            n=rem;
        }
        return m;
    }
    private static int testMe(int i){
        return i>>=1;
    }
}
