package org.thinking.in.concurrency.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author bxl
 * @date 2022/3/22
 */
public class SynchronizedUpgradeDemo {

    /**
     * 描述:锁升级：偏向锁->轻量级锁->重量级锁
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        // 休眠5s ， JVM启动了偏向锁
        Thread.sleep(5000);

        Object obj = new Object();

        // 虚拟机启动偏向锁
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        new Thread(()->{
            synchronized (obj){
                //第一个线程进入，偏向锁
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();

        //等上一个线程执行结束，此处在模拟让两个线程交替执行
        Thread.sleep(2000);

        new Thread(()->{
            synchronized (obj){
                //存在两个线程交替执行，升级为轻量级锁
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();

        //等待上一次线程执行结束
        Thread.sleep(2000);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized (obj){
                    //多个线程争抢，升级为重量级锁
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }).start();
        }
    }
}
