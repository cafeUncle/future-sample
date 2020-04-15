package com.yang.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class WhenCompleteCase {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
//            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
//            }
            System.out.println("run end ...");

            return "done";
        });

        // 无论是否发生异常，都在future结束后触发。如有异常则串行在exceptionally后触发。exceptionally是否正常结束不影响whenComplete
        // whenComplete 和 whenCompleteAsync 的区别：
        // whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
        // whenCompleteAsync：是把 whenCompleteAsync 这个任务继续提交给线程池来进行执行，但与exceptionally依然是串行的
        future.whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable action) {
                System.out.println("执行完成！");
            }

        });
        future.exceptionally(new Function<Throwable, String>() { // 异常时触发
            @Override
            public String apply(Throwable t) {
                System.out.println("执行失败！" + t.getMessage());
//                int i = 1/0;
//                System.out.println("exceptionally里也发生了异常时，则阻塞该段逻辑。但不影响 whenComplete 执行");

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "发生了异常：" + t.getMessage();
            }
        });

        // 没有指定线程池，没有.get .join，故需sleep阻塞等待future完成。
        TimeUnit.SECONDS.sleep(5);
    }
}
