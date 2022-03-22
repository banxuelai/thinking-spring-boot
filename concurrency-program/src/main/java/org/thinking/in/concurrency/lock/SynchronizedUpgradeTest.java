package org.thinking.in.concurrency.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author bxl
 * @date 2022/3/22
 */
public class SynchronizedUpgradeTest {

    /**
     * 描述:锁升级：无锁->轻量级锁->重量级锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{

        // 创建对象
        Object obj  = new Object();

        // 无锁
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        // 轻量级锁
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        // 多线程竞争，轻量级锁升级为重量级锁
        Thread.sleep(2000);
        for(int i = 0; i < 2; i++) {
            new Thread(
                    () -> {
                        synchronized (obj) {
                            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                        }
                    }
            ).start();
        }
    }
}
