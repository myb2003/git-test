package com.home;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String [] args) throws ExecutionException, InterruptedException {
//        CompletableFutureVoid();

//        创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        创建CompletableFuture带返回值的静态方法
        CompletableFuture<String> futureString = CompletableFuture.supplyAsync(() -> {
//            获取当前线程的名字
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello suppllyAsync";
        },executorService);
//        获取线程返回值
        System.out.println(futureString.get());
//        关闭线程池
        executorService.shutdown();
    }

    private static void CompletableFutureVoid() throws InterruptedException, ExecutionException {
        //        创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        创建CompletableFuture无返回值静态接口
        CompletableFuture<Void> futureVoid = CompletableFuture.runAsync(() -> {
//            获取当前线程的名字
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },executorService);
        System.out.println(futureVoid.get());
//        关闭线程池
        executorService.shutdown();
    }
}
