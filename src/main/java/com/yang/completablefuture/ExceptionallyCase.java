package com.yang.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExceptionallyCase {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                int i = 1/0;
            }catch (Exception e) {
                e.printStackTrace();
                completableFuture.completeExceptionally(e);
            }finally {
                completableFuture.complete("ok");
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
