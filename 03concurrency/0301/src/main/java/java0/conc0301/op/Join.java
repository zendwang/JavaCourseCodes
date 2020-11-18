package java0.conc0301.op;

import org.openjdk.jol.info.ClassLayout;

/**
 * t.join() /t.join(long millis)
 * 当前线程调用其他线程t的join方法，当前线程进入WAITING/TIMED_WAITING状态，
 * 当前线程不会释放已经持有的对象锁，线程t执行完毕或者millis时间到，当前线程进入就绪状态
 */
public class Join {
    private static final String SPLITE_STR = "===========================================";
    private static void printf(Object obj) {
        System.out.println(SPLITE_STR);
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(SPLITE_STR);
    }
    public static void main(String[] args) {
        Object oo = new Object();
    
        MyThread thread1 = new MyThread("thread1 -- ");
        thread1.setOo(oo);
        thread1.start();
        synchronized (oo) { //thread1.join();主线程阻塞，thread1 继续执行
            printf(oo);
        //synchronized (oo) { //thread1.join(); 主线程，thread1 都相互持有obj 对象锁，阻塞等待
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
    
}

class MyThread extends Thread {
    private static final String SPLITE_STR = "**************************************";
    private static void printf(Object obj) {
        System.out.println(SPLITE_STR);
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(SPLITE_STR);
    }
    private String name;
    private Object oo;
    
    public void setOo(Object oo) {
        this.oo = oo;
    }
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        synchronized (oo) {
            printf(oo);
        //synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
    
}