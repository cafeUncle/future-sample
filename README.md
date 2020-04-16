# future-sample


future用了许久，但没有对每个api的特性及背后的实现关注过。

开个repo，记录下学习过程吧！


|方法名|描述|
|-|-|
|runAsync(Runnable runnable)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码|
|runAsync(Runnable runnable, Executor executor)|使用指定的thread pool执行异步代码|
|supplyAsync(Supplier<U> supplier)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值|
|supplyAsync(Supplier<U> supplier, Executor executor)|使用指定的thread pool执行异步代码，异步操作有返回值|
|...|...|

> 参考：
> [Java并发包之阶段执行之CompletionStage接口](https://www.cnblogs.com/txmfz/p/11266411.html)
> [apply和compose的区别](https://www.jianshu.com/p/d78eb6866fbb)

* Complete
  * supplyAsync
  * runAsync
  * allOf
  * anyOf
* then
  * thenApply
  * thenApplyAsync
  * thenAccept
  * thenAcceptBoth
  * thenAcceptAsync
  * thenAcceptBothAsync
  * thenCompose
  * thenComposeAsync
  * thenCombine
  * thenCombineSync
  * thenRun
  * thenRunAsync
* run
  * runAfter
  * runAfterAsync
  * runAfterBoth
  * runAfterBothAsync
* either
  * applyToEither
  * applyToEitherAsync
  * acceptEither
  * acceptEitherAsync
  * runAfterEither
  * runAfterEitherAsync
* handle
  * handle
  * handleAsync
* complete
  * complete
  * completeAsync
  * whenComplete
  * whenCompleteAsync
  * completeOnTimeout
  * newInCompleteFuture
  * toCompletableFuture
* exceptionally
  * exceptionally
  * exceptionallyAsync
  * exceptionallyCompose
  * exceptionallyComposeAsync
  * completeExceptionally
  * isCompleteExceptionally
* other
  * copy
  * orTimeout
  * obtrubeException

等等


