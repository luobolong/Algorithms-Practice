package com.luobo.interview;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


class SimpleLogger {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void log(String message) {
        String currentTime = sdf.format(new Date());
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("[%s] [Thread-%d] [%s] %s", currentTime, threadId, threadName, message));
    }
}

/**
 * 产生数据接口
 */
interface DataLoader {
    List<Integer> loadData();
}

/**
 * 产生较多的数据
 */
class BigDataLoaderImpl implements DataLoader {
    @Override
    public List<Integer> loadData() {
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    }
}

/**
 * 产生少量数据
 */
class SmallDataLoaderImpl implements DataLoader {
    @Override
    public List<Integer> loadData() {
        return Arrays.asList(21, 22);
    }
}

/**
 * 产生空列表
 */
class EmptyDataLoaderImpl implements DataLoader {
    @Override
    public List<Integer> loadData() {
        return new ArrayList<>();
    }
}

/**
 * 产生null
 */
class NullDataLoaderImpl implements DataLoader {
    @Override
    public List<Integer> loadData() {
        return null;
    }
}

/**
 * 抛出异常
 */
class ErrorDataLoaderImpl implements DataLoader {
    @Override
    public List<Integer> loadData() {
        throw new RuntimeException("Error when loading data");
    }
}

/**
 * 随机产生数据接口实现类，随机产生不同的列表
 */
class RandomDataLoaderImpl implements DataLoader {
    private static class PresetData {
        List<Integer> dataList;
        double probability;

        PresetData(List<Integer> dataList, double probability) {
            this.dataList = dataList;
            this.probability = probability;
        }
    }

    private List<PresetData> presetDataList;

    public RandomDataLoaderImpl() {
        presetDataList = new ArrayList<>();

        // 产生较多的数据，概率 0.3
        presetDataList.add(new PresetData(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), 0.3));

        // 产生较少的数据，概率 0.1
        presetDataList.add(new PresetData(Arrays.asList(21, 22), 0.1));

        // 返回空数组，概率 0.5
        presetDataList.add(new PresetData(new ArrayList<>(), 0.5));

        // 返回null，概率 0.1
        presetDataList.add(new PresetData(null, 0.1));
    }

    @Override
    public List<Integer> loadData() {
        Random rand = new Random();

        // 有 10% 的概率会抛出异常
        if (rand.nextDouble() < 0.1) {
            throw new RuntimeException("Error when loading data");
        }

        // 随机返回一个列表
        double randomValue = rand.nextDouble();
        double cumulativeProbability = 0.0;
        for (PresetData presetData : presetDataList) {
            cumulativeProbability += presetData.probability;
            if (randomValue <= cumulativeProbability) {
                List<Integer> data = new ArrayList<>(presetData.dataList);
                SimpleLogger.log("Return data list: " + data);
                return data;
            }
        }
        return new ArrayList<>();
    }
}

/**
 * 处理数据接口
 */
interface DataProcessor {
    boolean process(Integer data);
}

/**
 * 随机处理数据实现类
 */
class RandomDataProcessorImpl implements DataProcessor {

    @Override
    public boolean process(Integer data) {
        Random rand = new Random();

        // 有 5% 的概率会抛出异常
        if (rand.nextDouble() < 0.05) {
            throw new RuntimeException("Error when processing data: " + data);
        }

        // 有 10% 返回false
        if (rand.nextDouble() < 0.1) {
            SimpleLogger.log("Processing data returns false: " + data);
            return false;
        }

        SimpleLogger.log("Processing data returns true: " + data);
        return true;
    }
}

/**
 * 这道题要实现一个性能高、健壮的生产者-消费者模型，我以后再完善这道题目的解法
 * 我做的很差，没理解题意，答非所问，代码写得烂，今天不出意外的挂掉了，真的很难过，有些崩溃
 * 历经那么多次的面试失败后，我反思自己的能力是太差了，技术能力差，工作能力差，真的什么都不行，唉
 */
public class ProducerAndConsumer {
    public static void main(String[] args) throws InterruptedException {
        DataLoader dataLoader = new RandomDataLoaderImpl();
        DataProcessor dataProcessor = new RandomDataProcessorImpl();
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        AtomicBoolean isRunning = new AtomicBoolean(true);

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            while (isRunning.get()) {
                try {
                    List<Integer> data = dataLoader.loadData();
                    if (data != null && !data.isEmpty()) {
                        for (Integer item : data) {
                            if (!queue.offer(item, 5000, TimeUnit.MILLISECONDS)) {
                                // 队列满，舍弃数据，暂停一下
                                SimpleLogger.log("Timeout when inserting data: " + item);
                                Thread.sleep(5000);
                            }
                        }
                    } else {
                        // 数据为空，暂停一下
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    SimpleLogger.log(e.getMessage());
                }
            }
        });
        producerThread.setName("Producer-1");
        producerThread.start();

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            while (isRunning.get() || !queue.isEmpty()) {
                try {
                    Integer data = queue.poll(500, TimeUnit.MILLISECONDS);
                    if (data != null) {
                        dataProcessor.process(data);
                    }
                } catch (Exception e) {
                    SimpleLogger.log(e.getMessage());
                }
            }
        });
        consumerThread.setName("Consumer-1");
        consumerThread.start();

        // 等待十分钟，结束
        Thread.sleep(10 * 60 * 1000L);
        isRunning.set(false);
    }
}
