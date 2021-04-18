package com.java.designpattern;

// 饿汉模式
//public class Singleton{
//    private static final Singleton singleton = new Singleton();
//    private Singleton(){ }
//
//    public static Singleton getInstance(){
//        return singleton;
//    }
//
//    public static void main(String[] args) {
////        System.out.println("hhahhahah");
//        System.out.println(Singleton.getInstance());
//    }
//}

// 懒汉模式
public class Singleton{
    private static Singleton singleton;
    private Singleton(){}
    synchronized public static Singleton getInstance(){
        if(singleton==null)
            singleton = new Singleton();
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}

// 双重加锁模式
//public class Singleton{
//    private static volatile Singleton singleton;
//
//    private Singleton(){}
//
//    public static Singleton getInstance(){
//        if(singleton==null){
//            synchronized (Singleton.class){
//                if(singleton==null){
//                    singleton = new Singleton();
//                }
//            }
//        }
//
//        return singleton;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Singleton.getInstance());
//    }
//}