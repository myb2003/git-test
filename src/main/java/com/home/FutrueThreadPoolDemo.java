package com.home;

import java.util.concurrent.*;

public class FutrueThreadPoolDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        m1();
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try{ TimeUnit.MILLISECONDS.sleep(500); }catch (Exception e){ e.printStackTrace();}
            return "task1 over";
        });
        FutureTask<String> futureTask3 = new FutureTask<String>(() -> {
            try{ TimeUnit.MILLISECONDS.sleep(300); }catch (Exception e){ e.printStackTrace();}
            return "task3 over";
        });
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try{ TimeUnit.MILLISECONDS.sleep(400); }catch (Exception e){ e.printStackTrace();}
            return "task1 over";
        });

        executorService.submit(futureTask1);
        executorService.submit(futureTask2);
        executorService.submit(futureTask3);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
        long endTime = System.currentTimeMillis();
        System.out.println("----cost:"+(endTime-startTime)+"毫秒");
        System.out.println(Thread.currentThread().getName()+"\t---end");
    }

    private static void m1() {
        long startTime = System.currentTimeMillis();
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (Exception e){ e.printStackTrace();}
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (Exception e){ e.printStackTrace();}
        try{
            TimeUnit.MILLISECONDS.sleep(600);
        }catch (Exception e){ e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("----cost:"+(endTime-startTime)+"毫秒");
        System.out.println(Thread.currentThread().getName()+"\t---end");
    }

}
