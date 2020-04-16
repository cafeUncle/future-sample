# future-sample


future用了许久，但没有对每个api的特性及背后的实现关注过。

开个repo，记录下学习过程吧！


|方法名|描述|
|-|-|
|runAsync(Runnable runnable)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码|
|runAsync(Runnable runnable, Executor executor)|使用指定的thread pool执行异步代码|
|supplyAsync(Supplier<U> supplier)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值|
|supplyAsync(Supplier<U> supplier, Executor executor)|使用指定的thread pool执行异步代码，异步操作有返回值|


> 参考：
> [Java并发包之阶段执行之CompletionStage接口](https://www.cnblogs.com/txmfz/p/11266411.html)
> [apply和compose的区别](https://www.jianshu.com/p/d78eb6866fbb)


改天会把几个方法按阶段或其他区分
如
* Complete
  * supply
  * run
  * all
* then
  * apply
  * accept
  * compose
  * combine
  * run
  * thenAcceptBoth
* either
  * acceptEither
  * applyToEitherCase
* handle

等等


  