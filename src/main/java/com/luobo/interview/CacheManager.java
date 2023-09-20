package com.luobo.interview;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程读取数据的时候，先读取缓存，如果缓存没有就读取数据库，并重新写入缓存，其他线程等待缓存写入完成后再读取缓存
 * 提供几个方法String getCache(String key). String getDb(String key), void writeCache(String key, Sting value)
 */
public class CacheManager {
    // 模拟缓存
    private static ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    // 存储每个key对应的锁
    private static ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    /**
     * 模拟读取缓存
     *
     * @param key 数据的key
     * @return 数据的value
     */
    public static String getCache(String key) {
        String value = cache.get(key);
        if (value != null) {
            return "Cache value: " + value;
        } else {
            return null;
        }
    }

    /**
     * 模拟读取数据库
     *
     * @param key 数据的key
     * @return 数据的value
     */
    public static String getDb(String key) {
        return "DB value: " + key;
    }

    /**
     * 写入缓存
     *
     * @param key   数据的key
     * @param value 数据的value
     */
    public static void writeCache(String key, String value) {
        cache.put(key, value);
    }

    /**
     * 实现读取数据的逻辑
     *
     * @param key 数据的key
     * @return 数据的value
     */
    public static String getData(String key) {
        String value = getCache(key);
        if (value != null) {
            return value;
        }

        // 获取该key的锁活或创建一个锁
        ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());

        // 获取锁
        lock.lock();
        try {
            // 再次检查缓存，因为其他线程可能更新了缓存
            value = getCache(key);
            if (value != null) {
                return value;
            }

            // 从数据库中读取数据
            value = getDb(key);

            // 更新缓存
            writeCache(key, value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":\t" + CacheManager.getData("1"));
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":\t" + CacheManager.getData("2"));
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":\t" + CacheManager.getData("1"));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":\t" + CacheManager.getData("2"));
        }).start();
    }
}
