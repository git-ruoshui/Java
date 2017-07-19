package com.zgc.common;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-10.17:45
 * Connect: 1072238017@qq.com
 * 冒泡排序
 */
public class Bubble {
    public static void main(String[] args) {
        int array[] = {11, 2, 5, 82, 7, 0, 4, 89, 72, 42, 16, 34, 58, 23};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        for (int x : array) {
            System.out.println(x);

        }

    }
}

