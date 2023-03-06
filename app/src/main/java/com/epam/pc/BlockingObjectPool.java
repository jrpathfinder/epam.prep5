package com.epam.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingObjectPool<T> {

    private final BlockingQueue<T> objects;
    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.objects = new ArrayBlockingQueue<T>(size, false);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public T get() throws InterruptedException {
        return this.objects.take();
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(T object) throws InterruptedException {
        this.objects.put(object);
    }
}