package com.yang.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionallyCase {

    public static void main(String[] args) {
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

    }
}
