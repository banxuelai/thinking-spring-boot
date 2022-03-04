package org.thinking.in.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author bxl
 * @date 2022/3/4
 */
@Slf4j
public class MakeTeaNotWait {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 启动两个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 创建线程2的futureTask
        FutureTask<String> ft2 = new FutureTask<String>(new T2Task());

        // 创建线程1的futureTask
        FutureTask<String> ft1 = new FutureTask<String>(new T1Task(ft2));

        executorService.submit(ft1);
        executorService.submit(ft2);

        executorService.shutdown();

    }
    static class T1Task implements Callable<String> {

        private FutureTask<String> ft2;

        public T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {

            log.info("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);

            String t2Result = ft2.get();

            log.info("T1 拿到T2的 {}， 开始泡茶", t2Result);

            return "T1: 上茶！！！";

        }
    }
    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {

            log.info("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            log.info("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);

            return "T2:绿茶拿到了";
        }
    }
}
