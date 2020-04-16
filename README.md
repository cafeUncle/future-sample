# future-sample


future用了许久，但没有对每个api的特性及背后的实现关注过。

开个repo，记录下学习过程吧！


|方法名|描述|
|-|-|
|runAsync(Runnable runnable)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码|
|runAsync(Runnable runnable, Executor executor)|使用指定的thread pool执行异步代码|
|supplyAsync(Supplier<U> supplier)|使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值|
|supplyAsync(Supplier<U> supplier, Executor executor)|使用指定的thread pool执行异步代码，异步操作有返回值|
