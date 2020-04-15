package com.yang.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * runAsync 和 supplyAsync 方法的区别是runAsync返回的CompletableFuture是没有返回值的
 * 不过依然可以连消费型的then,即thenAccept,只是入参是null
 */
public class RunAsyncCase {

    public static void main(String[] args) {

        CompletableFuture<Void> completeFuture = CompletableFuture
                .runAsync(() -> {
                    System.out.println("begin");
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = 2 / 0; // 异常会被吃掉 -> ExceptionallyCase.java
                    System.out.println("run Async");
                })
                .thenApply((o) -> o + " apply")
                .thenAccept(o -> System.out.println(o + " accept"));

    }
}
