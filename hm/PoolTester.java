package org.example.lesson22.hm;

//1.     Создайте класс PoolTester и
//a. в main создайте ForkJoinPool и
//b. запустите на нем 5 Runnable каждая из которых
//c. ждет рандомное время от 0 до 500 и распечатывает это время на экране
//d. дождитесь в main остановки ForkJoinPool

import org.example.lesson23.MapTester;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PoolTester {
    public static void main(String[] args) throws InterruptedException {
//        a. в main создайте ForkJoinPool и
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        b. запустите на нем 5 Runnable каждая из которых
        for (int i = 0; i < 5; i++) {
            forkJoinPool.submit(new MyRunnable());
        }

        // d. Дожидаемся остановки ForkJoinPool
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // c. Ждем рандомное время от 0 до 500 и распечатываем это время на экране
            int randomTime = ThreadLocalRandom.current().nextInt(500);
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waited: " + randomTime + " ms");
        }
    }
}
