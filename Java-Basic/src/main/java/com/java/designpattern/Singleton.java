package com.java.designpattern;


public class Singleton {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
    }
    
    
    /**
     * 双重加锁模式
     */
    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }

    /**
     * 饿汉模式
     */
   // private static final Singleton singleton = new Singleton();
   // private Singleton(){ }
   //
   // public static Singleton getInstance(){
   //     return singleton;
   // }


    /**
     * 懒汉模式
     */
   // private static Singleton singleton;
   //
   // private Singleton() {
   // }
   //
   // synchronized public static Singleton getInstance() {
   //     if (singleton == null)
   //         singleton = new Singleton();
   //     return singleton;
   // }
   
}