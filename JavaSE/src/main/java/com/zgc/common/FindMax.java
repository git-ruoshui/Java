package com.zgc.common;


/**
 * Author: ZhangGuangchao
 * Date: 2017-07-07.11:31
 * Connect: 1072238017@qq.com
 * 递归实现数组中最大值--练习chapter1-1.6
 */
public class FindMax {

    public static void main(String[] args) {
       // System.out.println(Max({1,2,5,7,3},0,4));
    }
    //str字符的所有排列顺序（abc,acb,bac,bca...）
    private void permute(String str){

    }
    /**
     *
     * @param str 数组
     * @param low 数组下标
     * @param high 数组上标
     * @return 最大值
     */
    private int  Max(char[]str,int low,int high){
        int max;
        if(low > high-2) {
            if(str[low] > str[high]) max = str[low];
            else max = str[high];
        }else {
            int mid = (low + high)/2;
            int max1 = Max(str, low, mid);
            int max2 = Max(str, mid+1, high);
            max = max1>max2 ? max1 : max2;
        }
        return max;
    }
}
