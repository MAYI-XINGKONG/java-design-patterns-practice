package com.niudada.proxydemo.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CacheTest {

    @Test
    public void testCache() {
        DataService realService = new DatabaseDataService();
        DataService proxy = new CachingDataProxy(realService);
        log.info("第一次请求：{}", proxy.fetchData("hello"));
        log.info("第二次请求：{}", proxy.fetchData("hello"));
        log.info("第三次请求：{}", proxy.fetchData("world"));
        log.info("第四次请求：{}", proxy.fetchData("world"));
    }
}
