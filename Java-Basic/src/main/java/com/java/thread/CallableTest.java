package com.java.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 测试：Callable的返回值！！！
 * 参考：https://www.cnblogs.com/syp172654682/p/9788051.html
 */

/* 
 * @Author: sunyd 
 * @Description:  
 * @Date: 2021/1/4 22:34 
 * @Param:  
 * @return:  
 **/
public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //创建一个Callable，3秒后返回String类型
        Callable myCallable = new Callable() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println("calld方法执行了");
                return "[Callable return!]";
            }
        };
        
        System.out.println("提交任务之前 " + getStringDate());
        Future future = executor.submit(myCallable);


        System.out.println("提交任务之后，获取结果之前 " + getStringDate());
        System.out.println("获取返回值: " + future.get());
        System.out.println("获取到结果之后 " + getStringDate());
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

}

