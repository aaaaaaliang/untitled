package com.zxl.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //把一维数组0~15打乱顺序
        //定义一个数组
        int[] tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //打乱数组中的数据的顺序
        //遍历数组，得到每一个元素，拿着每一个元素跟索引上的数据进行交换
        Random r=new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取到随机索引
            int index=r.nextInt(tempArr.length);
            //拿着遍历的数据跟随机索引上的数据进行交换
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }
        //创建一个二维数组
        int[][] data=new int[4][4];
        //给二维数组添加数据
        for (int i = 0; i < tempArr.length; i++) {
            data[i/4][i%4]=tempArr[i];
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.println(data[i][j]+" ");
            }
            System.out.println();
        }

    }
}
