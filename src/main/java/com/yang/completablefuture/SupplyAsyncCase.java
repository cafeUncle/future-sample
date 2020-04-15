package com.yang.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture since jdk 1.8
 */
public class SupplyAsyncCase {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("执行开始！");
                Thread.sleep(3000);
                System.out.println("执行结束！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenApply((res) -> {  // thenApplyAsync
            return res + " world!";
        });

        // sleep的时候 future已经在执行了
        Thread.sleep(5000);

        String join = completableFuture.join();

        System.out.println("join apply结束了！" + completableFuture.get());

        completableFuture.thenAccept((result) -> {  // thenApplyAsync
            System.out.println("future accept一下！" + result);
        }).join();

//        future.get(); // 用get可以指定超时参数 join只能阻塞主线程

        System.out.println("main线程结束了！" + completableFuture.get());
    }
}
