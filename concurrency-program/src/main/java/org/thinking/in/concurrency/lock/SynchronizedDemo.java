package org.thinking.in.concurrency.lock;

/**
 * @author bxl
 * @date 2022/3/21
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            System.out.print("synchronized通过对象监视器monitor来实现同步");
        }
    }

    public static synchronized void show() {
        System.out.print("synchronized通过对象监视器monitor来实现同步+++show");
    }
}
