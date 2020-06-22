package com.yang.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ExceptionallyCase {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                e.printStackTrace();
                completableFuture.completeExceptionally(e); // get 时 输出 异常
            } finally {
                // future调用complete(T t)会立即执行。但是complete(T t)只能调用一次，后续的重复调用会失效。
                completableFuture.complete("ok");  // 指定 get 时 的 返回 结果
            }
        }).start();

        String result = null;
        try {
            result = completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(result);


        // ----

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
//            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
//            }
            System.out.println("run end ...");
        });



//        try {
//            Void aVoid = future.get(); // 不调用 get, 就不会向父线程抛出异常。 如果想获取异常，可以用 exceptionally
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        future.exceptionally(t -> {
//            System.out.println("执行失败！" + t.getMessage());
//            return null;
//        });

        TimeUnit.SECONDS.sleep(2);
    }
}
