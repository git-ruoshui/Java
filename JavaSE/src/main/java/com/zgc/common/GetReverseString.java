package com.zgc.common;

import java.util.*;

/**
 * Author: ZhangGuangchao
 * Date: 2017-07-06.13:13
 * Connect: 1072238017@qq.com
 * 字符串倒序输出
 */
public class GetReverseString {
    public static void main(String[] args) {
        String str = "1234567890";
        //方法1
        getReverseString(str);
        //方法2
        String strRev="";
        char[] charArray = str.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            strRev+=charArray[i];
        }
        System.out.println(strRev);

       // String word="we come from China";



        Integer[] strint={2,1,3,2,5,7,6,9,7,9,4,10,12,11,12,10};
        quChong2(strint);
    }

    private static void getReverseString(String str) {
        StringBuffer stringBuffer=new StringBuffer(str);
        String s=stringBuffer.reverse().toString();
        System.out.println(s);
    }

    //倒序输出单词，单词顺序不变
    public static void wordRev(String word){
        String[]wordRev=word.split(" ");
        String wordResult="";
        for (int i = wordRev.length-1; i >=0 ; i--) {
            wordResult+=wordRev[i]+" ";
        }
        System.out.println(wordResult);
    }
    //整型数组去重1
    public static void quChong(Integer[] ints){
        List<Integer> intList=new ArrayList<Integer>();
        for (int i = 0; i <ints.length-1 ; i++) {
            if(!intList.contains(ints[i])){
                intList.add(ints[i]);
            }
        }
        System.out.println(intList);
    }
    //数组去重2
    public static void quChong2(Integer[]ints){
        HashSet<Integer> hashSet=new HashSet<>(Arrays.asList(ints));
        //TreeSet<Integer> treeSet=new TreeSet<>(Arrays.asList(ints));//得结果后会自动排序
        Iterator i = hashSet.iterator();
        while(i.hasNext()){
            System.out.print(i.next()+System.getProperty("line.separator"));//换行
        }
        /*
        HashSet有以下特点
         不能保证元素的排列顺序，顺序有可能发生变化
         不是同步的
         集合元素可以是null,但只能放入一个null
        1、TreeSet 是二差树实现的,Treeset中的数据是自动排好序的，不允许放入null值。
        2、HashSet 是哈希表实现的,HashSet中的数据是无序的，可以放入null，但只能放入一个null，两者中的值都不能重复，就如数据库中唯一约束。
        3、HashSet要求放入的对象必须实现HashCode()方法，放入的对象，是以hashcode码作为标识的，而具有相同内容的 String对象，
        hashcode是一样，所以放入的内容不能重复。但是同一个类的对象可以放入不同的实例 。
         */
    }
    public static void intTobyte(){
        int i=1044;
        byte b=(byte)i;
        System.out.println(b);
    }
}
