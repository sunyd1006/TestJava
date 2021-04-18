package com.javaguide.design;

public class SiglitionDemo {
    private SiglitionDemo(){};       //私有化构造方法
    private static volatile SiglitionDemo singleTon=null;
    public static SiglitionDemo getInstance(){
        //第一次校验
        if(singleTon==null){
            synchronized(SiglitionDemo.class){
                //第二次校验
                if(singleTon==null){
                    singleTon=new SiglitionDemo();
                }
            }
        }
        return singleTon;
    }
    public static void main(String[]args){
        for(int i=0;i<200;i++){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+ SiglitionDemo.getInstance().hashCode());
                }
            }).start();
        }
    }
}