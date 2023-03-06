What is concurrency
Thread API
Java memory model
Semaphore, CountDownLatch, CyclicBarrier
Completable future, parallel streams
Anatomy of Synchronization
Locks
Atomics
Thread pools
Concurrent collections

Assignment #1

Create HashMap<Integer, Integer>.
The first thread ad
Threads should work before catching ConcurrentModificationException.
Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
What has happened after simple Map implementation exchanging? How it can be fixed in code?
Try to write your custom ThreadSafeMap with synchronization and without.
Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance. 
Provide a simple report to your mentor.

REPORT ConcurrentHashMap, 10_000 elements, 100 sum iterations
-------------------------------------------------------------
corretto-1.8.0_362                    |  0.1054104 seconds
Eclipse Adoptium\jdk-11.0.15.10       |  0.1277532 seconds
Eclipse Adoptium\jdk-17.0.2.8-hotspot |  0.1410829 seconds

Assignment #2

Create three threads:
•	1st thread is infinitely writing random number to the collection;
•	2nd thread is printing sum of the numbers in the collection;
•	3rd is printing square root of sum of squares of all numbers in the collection.
Make these calculations thread-safe using synchronization block. Fix the possible deadlock.

Assignment #3

Implement message bus using Producer-Consumer pattern.
1.	Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
2.	Implement producer, which will generate and post randomly messages to the queue.
3.	Implement consumer, which will consume messages on specific topic and log to the console message payload.
4.	(Optional) Application should create several consumers and producers that run in parallel.

Assignment #4

Create simple object pool with support for multithreaded environment. 
No any extra inheritance, polymorphism or generics needed here, just implementation of simple class:

/**
* Pool that block when it has not any items or it full
*/
public class BlockingObjectPool {

    /**
    * Creates filled pool of passed size
    *
    * @param size of pool
    */
    public BlockingObjectPool(int size) {
    
    }

    /**
    * Gets object from pool or blocks if pool is empty
    *
    * @return object from pool
    */
    public Object get() {
    
    }

    /**
    * Puts object to pool or blocks if pool is full
    *
    * @param object to be taken back to pool
    */
    public void take(Object object) {
        
    }
}

Use any blocking approach you like.

Assignment #5

Make an application that contains business logic for making exchange operations between different currencies.
1. Create models for dealing with currencies, user accounts and exchange rates. One account can have multiple currency values. 
Use BigDecimal for performing of exchange calculations.

2. Data with user accounts should be stored as files (one file per account).
3. Separate application functionality to DAO, service and utilities.
4. Create module which will provide high-level operations (manage accounts, currencies, exchange rates).
5. Create sample accounts and currencies. Define sample exchange rates.
6. Provide concurrent data access to user accounts. Simulate simultaneous currency exchanges for single account 
by multiple threads and ensure that all the operations are thread-safe.
7. Use ExecutorService to manage threads.
8. Make custom exceptions to let user to know the reason of error. Do not handle runtime exceptions.
9. Validate inputs such an account existence, sufficiency of currency amount, etc.
10. Log information about what is happening on different application levels and about conversion results. Use Logger for that.