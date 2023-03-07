package com.home;

import java.util.concurrent.*;

public class CompletableFutureuseDemo
{
    public static void main(String[] args) throws InterruptedException {
//        Thread1();

//        创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(()->{
                System.out.println(Thread.currentThread().getName());
                System.out.println("一秒后出结果");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = ThreadLocalRandom.current().nextInt(10);
                System.out.println(i);
                return i;
            },executorService).whenComplete((v,e)->{
                if (e==null){
                    System.out.println("计算成功，系统更新结束："+v);
                }
            }).exceptionally(e->{
                e.printStackTrace();
                System.out.println("发生异常"+e.getCause().getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName()+"线程先去忙其他事情了");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();

        }

    }

    private static void Thread1() throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("一秒后出结果");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = ThreadLocalRandom.current().nextInt(10);
            System.out.println(i);
            return i;
        }).whenComplete((v,e)->{
            if (e==null){
                System.out.println("计算成功，系统更新结束："+v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println("发生异常"+e.getCause().getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName()+"线程先去忙其他事情了");
//        因为默认的线程池是跟随系统结束的，所有main线程结束后，Fork线程池也会自动关闭,所以可以选择main线程结束后，让main线程先睡眠一段时间的
        TimeUnit.SECONDS.sleep(3);
    }
}
