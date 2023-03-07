package com.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("taobao"),
            new NetMall("dangdang"),
            new NetMall("pdd"),
            new NetMall("tianmao")
            );

    public static List<String> getPrice(List<NetMall> list,String productName){
        return list
                .stream()
                .map(netMall ->
                        String.format(productName+" in %s price is %.2f",
                                netMall.getNetMallName(),netMall.calcPrice(productName))).collect(Collectors.toList());
    }

    public static List<String> getPriceCompletableFuture(List<NetMall> list,String productName){
        return list
                .stream()
                .map(netMall -> CompletableFuture.supplyAsync(()->
                    String.format(productName+" in %s price is %.2f",
                            netMall.getNetMallName(),netMall
                                    .calcPrice(productName))))
                .collect(Collectors.toList()).stream().map((e)->e.join()).collect(Collectors.toList())      ;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> mysql = getPrice(list, "mysql");
        for (String element : mysql) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间："+(endTime-startTime));

        System.out.println("-------------");

        long startTime2 = System.currentTimeMillis();
        List<String> mysql2 = getPriceCompletableFuture(list, "mysql");
        for (String element2 : mysql2) {
            System.out.println(element2);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("耗费时间："+(endTime2-startTime2));
    }
}

class NetMall{
    @Getter
    private String netMallName;
    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }
    public double calcPrice(String productName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

    }
}