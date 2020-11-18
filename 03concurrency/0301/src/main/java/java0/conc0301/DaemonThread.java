package java0.conc0301;

/**
 * daemon = false 后台守护线程标志 -- 决定JVM优雅退出
 * daemon = true 后台守护线程会不执行了
 */
public class DaemonThread {
    
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(false);
        thread.start();
        System.out.println("主线程" );
    }
    
    
}
