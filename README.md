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