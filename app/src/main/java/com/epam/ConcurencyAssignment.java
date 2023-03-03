package com.epam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConcurencyAssignment {

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i<10_000){
                    map.put(i,i);
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
                while(j<100) {
                    int sum = 0;
                    for (int value : map.values()) {
                        sum += value;
                    }
                    j++;
                    System.out.println(sum);
                }
                final long endTime   = System.nanoTime();
                final long totalTime = endTime - startTime;
                final double elapsedTimeInSecond = (double) totalTime / 1_000_000_000;

                System.out.println(elapsedTimeInSecond + " seconds");

            }
        });
        thread2.start();
    }
}