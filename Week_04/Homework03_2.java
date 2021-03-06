package java0.conc0303;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Homework03_2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        ExecutorService executor = Executors.newCachedThreadPool();
        int[] resultTmp = new int[1];
        Future<int[]> result = executor.submit(new Runnable() {
            @Override
            public void run() {
                resultTmp[0] = sum();
            }
        }, resultTmp);
        executor.shutdown();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result.get()[0]);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

}
