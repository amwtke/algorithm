package hashmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class test {
    private Map<Integer, Integer> hashMap = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(100);
    private static AtomicInteger counter = new AtomicInteger(0);

    @Before
    public void setUp() {
        hashMap.clear();
    }

    @Test
    public void hash_map_dead_lock() throws InterruptedException {
        log.info("begin");
        for (int repeat = 0; repeat < 100; repeat++) {
            for (int i = 0; i < 1000; i++) {
                executorService.execute(this::putSomethingToHashMap);
            }
            log.info("repeat:{}", repeat);
        }
        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            log.info("hash map count:{}", hashMap.size());
            log.info("counter count:{}", counter.get());
        }

        log.info("finished");
        log.info("hash map count:{}, counter:{}", hashMap.size(), counter.get());
    }

    @Test
    public void util() {
        int tmp = (1 << (32 - 16)) - 1;
        tmp = 8 >>> 3;
        log.info("value:{}", tmp);
    }

    private void putSomethingToHashMap() {
//        log.info(Thread.currentThread().getId() + "-in");
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int key = r.nextInt();
            int value = r.nextInt();
            hashMap.put(key, value);
            counter.incrementAndGet();
        }
//        log.info(Thread.currentThread().getId() + "-out");
    }
}
