package com.home;


import com.mysql.cj.util.TimeUtil;

import java.util.concurrent.*;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        API1();
//        ThenApply();
//        Thandle();
        //        创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }, executorService)
                .thenApply(f->{return f+1;}).thenApply(f->{return f+2;}).thenAccept(System.out::println);
//        关闭线程池

        extracted(executorService);
    }

    private static void extracted(ExecutorService executorService) {
        executorService.shutdown();
    }

    private static void Thandle() {
        //        创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executorService).handle((f, e) -> {
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("计算结果:" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("出现异常");
            return null;
        });
        System.out.println(Thread.currentThread().getName());
//        关闭线程池
        executorService.shutdown();
    }

    private static void ThenApply() {
        //        创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executorService).thenApply(f -> {
            System.out.println("222");
            return f + 2;
        }).thenApply(f -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("计算结果:" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("出现异常");
            return null;
        });
        System.out.println(Thread.currentThread().getName());
//        关闭线程池
        executorService.shutdown();
    }

    private static void API1() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
//        System.out.println(future.get(3,TimeUnit.SECONDS));
//        System.out.println(future.join());
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(future.getNow("hhh"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(future.complete("CompletableFuture") + "\t" + future.join());
    }
}