这里面有：

多线程实现：生产者消费者

单例模式：恶汉、懒汉、常用的

# 注解 和实现注解
- https://www.runoob.com/w3cnote/java-annotation.html

# idea debug 短点介绍
- run - viewBreakpoints
- https://cloud.tencent.com/developer/article/1497529
- 行短点可以设置条件
- excepiton断点可以catch 答应，不catch打印
- field 断点，可以观测参数的被修改的变化


```java
synchronized (obj) {
        /**
         * 个线程可以从挂起状态变为可以运行状态（也就是被唤醒），
         * 即使该线程没有被其他线程调用notify()、notifyAll()方法进行通知，或者被中断，或者等待超时，
         * 这就是所谓的虚假唤醒。虽然虚假唤醒在应用实践中很少发生，但要防患于未然
         */
    while (条件不满足){               
        obj.wait();               
    }    
 }
```

# 什么是同步和异步
- https://stackoverflow.com/questions/748175/asynchronous-vs-synchronous-execution-what-does-it-really-mean
- 同步：task = ABC三个小任务，必须以ABC的顺序执行；不然结果会有问题，三者有依赖关系，这是一个同步的过程
- 异步：task = ABC三个小人物，随便怎么执行ABC，只要都执行完了就行，可以ABC，也可以ACBCBA（把A又分片执行），三者没有依赖这是一个异步的过程；

```text

SYNCHRONOUS

You are in a queue to get a movie ticket. You cannot get one until everybody in front of you gets one, and the same applies to the people queued behind you.

ASYNCHRONOUS

You are in a restaurant with many other people. You order your food. Other people can also order their food, they don't have to wait for your food to be cooked and served to you before they can order. In the kitchen restaurant workers are continuously cooking, serving, and taking orders. People will get their food served as soon as it is cooked.
```

# 什么是双核四线程
> CPU双核，是指2个物理核心，4线程，是可以当作4个逻辑核使用（1个物理核心超线程为2个逻辑核心）
> 实际上1个物核心，还是只有1个线程可以在1个时间片里，可以跑2个线程的代码，但是有竞争条件；不是一定能跑2个线程的代码的。

# wait VS blocked
```java
- blocked: 就是等待获取锁的状态，比如等待进入sychronized同步代码快
- wait: 在同步代码块内，obj.wait() 等
        线程调用共享变量的wait()方法后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁，则这些锁是不会被释放的

A thread goes to wait state once it calls wait() on an Object. This is called Waiting State. Once a thread reaches waiting state, it will need to wait till some other thread calls notify() or notifyAll() on the object.

Once this thread is notified, it will not be runnable. It might be that other threads are also notified (using notifyAll()) or the first thread has not finished his work, so it is still blocked till it gets its chance. This is called Blocked State. A Blocked state will occur whenever a thread tries to acquire lock on object and some other thread is already holding the lock.

Once other threads have left and its this thread chance, it moves to Runnable state after that it is eligible pick up work based on JVM threading mechanism and moves to run state.

```


# Java线程6种状态
Java中线程的状态分为6种。
孙总结：
- 只有wait释放对象锁，sleep, yeild不释放
- sleep, yeild是让出cpu, 进入阻塞状态。




1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
   线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
3. 阻塞(BLOCKED)：表示线程阻塞于锁。
4. 等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
5. 超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
6. 终止(TERMINATED)：表示该线程已经执行完毕。

这6种状态定义在Thread类的State枚举中，可查看源码进行一一对应。

一、线程的状态图     
二、状态详细说明
1. 初始状态(NEW)
   实现Runnable接口和继承Thread可以得到一个线程类，new一个实例出来，线程就进入了初始状态。

2.1. 就绪状态(RUNNABLE之READY)
就绪状态只是说你资格运行，调度程序没有挑选到你，你就永远是就绪状态。
调用线程的start()方法，此线程进入就绪状态。
当前线程sleep()方法结束，其他线程join()结束，等待用户输入完毕，某个线程拿到对象锁，这些线程也将进入就绪状态。
当前线程时间片用完了，调用当前线程的yield()方法，当前线程进入就绪状态。
锁池里的线程拿到对象锁后，进入就绪状态。
2.2. 运行中状态(RUNNABLE之RUNNING)
线程调度程序从可运行池中选择一个线程作为当前线程时线程所处的状态。这也是线程进入运行状态的唯一的一种方式。

3. 阻塞状态(BLOCKED)
   阻塞状态是线程阻塞在进入synchronized关键字修饰的方法或代码块(获取锁)时的状态。

4. 等待(WAITING)
   处于这种状态的线程不会被分配CPU执行时间，它们要等待被显式地唤醒，否则会处于无限期等待的状态。

5. 超时等待(TIMED_WAITING)
   处于这种状态的线程不会被分配CPU执行时间，不过无须无限期等待被其他线程显示地唤醒，在达到一定时间后它们会自动唤醒。

6. 终止状态(TERMINATED)
   当线程的run()方法完成时，或者主线程的main()方法完成时，我们就认为它终止了。这个线程对象也许是活的，但是它已经不是一个单独执行的线程。线程一旦终止了，就不能复生。
   在一个终止的线程上调用start()方法，会抛出java.lang.IllegalThreadStateException异常。
   三、等待队列
   调用obj的wait(), notify()方法前，必须获得obj锁，也就是必须写在synchronized(obj) 代码段内。
   与等待队列相关的步骤和图


线程1获取对象A的锁，正在使用对象A。
线程1调用对象A的wait()方法。
线程1释放对象A的锁，并马上进入等待队列。
锁池里面的对象争抢对象A的锁。
线程5获得对象A的锁，进入synchronized块，使用对象A。
线程5调用对象A的notifyAll()方法，唤醒所有线程，所有线程进入同步队列。若线程5调用对象A的notify()方法，则唤醒一个线程，不知道会唤醒谁，被唤醒的那个线程进入同步队列。
notifyAll()方法所在synchronized结束，线程5释放对象A的锁。
同步队列的线程争抢对象锁，但线程1什么时候能抢到就不知道了。
四、同步队列状态
当前线程想调用对象A的同步方法时，发现对象A的锁被别的线程占有，此时当前线程进入同步队列。简言之，同步队列里面放的都是想争夺对象锁的线程。
当一个线程1被另外一个线程2唤醒时，1线程进入同步队列，去争夺对象锁。
同步队列是在同步的环境下才有的概念，一个对象对应一个同步队列。
线程等待时间到了或被notify/notifyAll唤醒后，会进入同步队列竞争锁，如果获得锁，进入RUNNABLE状态，否则进入BLOCKED状态等待获取锁。
五、几个方法的比较
Thread.sleep(long millis)，一定是当前线程调用此方法，当前线程进入TIMED_WAITING状态，但不释放对象锁，millis后线程自动苏醒进入就绪状态。作用：给其它线程执行机会的最佳方式。
Thread.yield()，一定是当前线程调用此方法，当前线程放弃获取的CPU时间片，但不释放锁资源，由运行状态变为就绪状态，让OS再次选择线程。作用：让相同优先级的线程轮流执行，但并不保证一定会轮流执行。实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。Thread.yield()不会导致阻塞。该方法与sleep()类似，只是不能由用户指定暂停多长时间。
thread.join()/thread.join(long millis)，当前线程里调用其它线程t的join方法，当前线程进入WAITING/TIMED_WAITING状态，当前线程不会释放已经持有的对象锁。线程t执行完毕或者millis时间到，当前线程一般情况下进入RUNNABLE状态，也有可能进入BLOCKED状态（因为join是基于wait实现的）。
obj.wait()，当前线程调用对象的wait()方法，当前线程释放对象锁，进入等待队列。依靠notify()/notifyAll()唤醒或者wait(long timeout) timeout时间到自动唤醒。
obj.notify()唤醒在此对象监视器上等待的单个线程，选择是任意性的。notifyAll()唤醒在此对象监视器上等待的所有线程。
LockSupport.park()/LockSupport.parkNanos(long nanos),LockSupport.parkUntil(long deadlines), 当前线程进入WAITING/TIMED_WAITING状态。对比wait方法,不需要获得锁就可以让线程进入WAITING/TIMED_WAITING状态，需要通过LockSupport.unpark(Thread thread)唤醒。
六、疑问
等待队列里许许多多的线程都wait()在一个对象上，此时某一线程调用了对象的notify()方法，那唤醒的到底是哪个线程？随机？队列FIFO？or sth else？Java文档就简单的写了句：选择是任意性的（The choice is arbitrary and occurs at the discretion of the implementation）。


# obj.notify 后的过程：
- 唤醒被该obj阻塞的thread，唤醒那一个，就看随机
- 被唤醒的thread，还要等者进入sychronized的权限

# 1.5 sleep 效果
在这期间不参与CPU的调度，但是该线程所拥有的监视器资源，比如锁还是持有不让出的
- 子线程在睡眠期间，主线程中断了它，所以子线程在调用sleep方法处抛出了InterruptedException异常
- 在调用Thread.sleep(long  millis)时为millis参数传递了一个负数，则会抛出IllegalArgumentException异常
```java
try{
   Thead.sleep(1000);
}catch (InterruptedException e) {
   e.printStackTrace();
}
```

# yeild 效果
- 当一个线程调用yield方法时，实际就是在暗示线程调度器当前线程请求让出自己的CPU使用，但是线程调度器可以无条件忽略这个暗示。



this 关键字
- https://www.yiibai.com/java/this-keyword.html
this关键字可用来引用当前类的实例变量。
this关键字可用于调用当前类方法(隐式)。
this()可以用来调用当前类的构造函数。
this关键字可作为调用方法中的参数传递。
this关键字可作为参数在构造函数调用中传递。
this关键字可用于从方法返回当前类的实例。

```java
// 4
class S2 {
   void m(S2 obj) {
      System.out.println("method is invoked");
   }

   void p() {
      m(this);
   }

   public static void main(String args[]) {
      S2 s1 = new S2();
      s1.p();
   }
}
```

IDEA 第三方Jar的导入导出：
可以导入自定义的jar，别人可以使用，也可以用于交付。

