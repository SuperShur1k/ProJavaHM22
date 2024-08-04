package org.example.lesson22.hm;

//2. Создайте класс HomeWorkCallable
//a. создайте в нем метод public static int waitSomeTime который
//     спит рандомное время от 0 до 1000 мс и
//     возвращает это время в качестве результата
//b. Создайте и запустите два Callable каждый из которых выполняет waitSomeTime и возвращает результат
//с. В main получите и сложите эти результаты, запустив эти Callable на двух потоках используя FutureTask

import java.util.concurrent.*;

public class HomeWorkCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable1 = HomeWorkCallable::waitSomeTime;
        Callable<Integer> callable2 = HomeWorkCallable::waitSomeTime;

        FutureTask<Integer> futureTask1 = new FutureTask<>(callable1);
        FutureTask<Integer> futureTask2 = new FutureTask<>(callable2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(futureTask1);
        executorService.submit(futureTask2);

        int result1 = futureTask1.get();
        int result2 = futureTask2.get();
        int sum = result1 + result2;

        System.out.println("Result 1: " + result1 + "ms");
        System.out.println("Result 2: " + result2 + "ms");
        System.out.println("Sum: " + sum + "ms");

        executorService.shutdown();
    }//main

    public static int waitSomeTime() {
        int randomTime = ThreadLocalRandom.current().nextInt(1000);
        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return randomTime;
    }
}
