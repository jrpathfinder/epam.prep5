package com.epam;

import com.epam.pc.Consumer;
import com.epam.pc.DataQueue;
import com.epam.pc.Producer;
import com.epam.storage.InMemoryStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.epam.pc.ThreadUtil.sleep;
import static com.epam.pc.ThreadUtil.waitForAllThreadsToComplete;

public class ConcurencyAssignment {

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    private static final List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {

        //Assignment #1 DAS EXPERIMENT
        //task1();

        //Assignment #2 DEADLOCKS
        //task2();

        //Assignment #3 WHERE’S YOUR BUS, DUDE
        //task3();

        //Assignment #4
        //task4();

        //Assignment #5
        task5();

    }

    private static void task5() throws IOException {
        InMemoryStorage.init();
    }

    private static void task1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10_000) {
                    map.put(i, i);
                    i++;
                }
            }
        });
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                final long startTime = System.nanoTime();
                int j = 0;
                while (j < 100) {
                    int sum = 0;
                    for (int value : map.values()) {
                        sum += value;
                    }
                    j++;
                    System.out.println(sum);
                }
                final long endTime = System.nanoTime();
                final long totalTime = endTime - startTime;
                final double elapsedTimeInSecond = (double) totalTime / 1_000_000_000;

                System.out.println(elapsedTimeInSecond + " seconds");

            }
        });
        thread2.start();
    }
    private static void task2() {

        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 10_000 + 1);
                    synchronized (list) {
                        list.add(randomNum);
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Random value: " + randomNum);
                }
            }
        });
        writeThread.start();
        Thread sumThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int sum = 0;
                    synchronized (list) {
                        for (int value : list) {
                            sum += value;
                        }
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("SUM:" + sum);
                }
            }
        });
        sumThread.start();

        Thread squareThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int sum = 0;
                    synchronized (list) {
                        for (int value : list) {
                            sum += value * value;
                        }
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("SQ:" + Math.sqrt(sum));
                }
            }
        });
        squareThread.start();
    }
    private static void task3() {
        int MAX_QUEUE_CAPACITY = 100;
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
        int producerCount = 3;
        int consumerCount = 3;
        List<Thread> threads = new ArrayList<>();
        Producer producer = new Producer(dataQueue);
        for(int i = 0; i < producerCount; i++) {
            Thread producerThread = new Thread(producer);
            producerThread.start();
            threads.add(producerThread);
        }
        Consumer consumer = new Consumer(dataQueue);
        for(int i = 0; i < consumerCount; i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            threads.add(consumerThread);
        }

        // let threads run for two seconds
        sleep(2000);

        // Stop threads
        producer.stop();
        consumer.stop();

        waitForAllThreadsToComplete(threads);
    }

}