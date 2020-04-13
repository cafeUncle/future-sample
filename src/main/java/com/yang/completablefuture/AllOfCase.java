package com.yang.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllOfCase {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
            0, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1),
            r -> new Thread(r, "10个线程的池子"), new AbortPolicy());

//        List<CompletableFuture<String>> completableFutureList = IntStream.range(0, 10).boxed()
        List<CompletableFuture<String>> completableFutureList = Stream
            .of(1, 3, 9, 5, 6, 7, 8, 4, 5, 6)
            .map(i -> {
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(i * 1000L);
                        System.out.println(i + "hello");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i + "hello";
                }, executorService);
            }).collect(Collectors.toList());

        CompletableFuture.allOf(completableFutureList.toArray(CompletableFuture[]::new)).join();

        System.out.println("join 只需等待耗时最长的9s即可，无需 1 + 2 + 3 。4 。5 。6 。7 。8 。9 ...");
        System.out.println("猛然间想起来那个神奇的排序算法。。猴子排序！！！");

        // 通过这个case，也可以玩玩 executorService 的几个核心参数，通过不同参数对应的输出结果，得出每个参数的效果
        // 如   0  10 1 0 // 提出问题，core 0 max 10 list 0 时，为啥会报错？
        // 如   0  10 1 1 // 提出问题，core 0 max 10 list 1 时，为啥就不报错了？
        // 如   10 10 1 0
        // 如   10 10 1 1 // core>0时，线程一直在，即使任务执行完毕后，主线程仍然不会退出
        // 如   0  10 1 1 // 此时就会退出了
    }

}
