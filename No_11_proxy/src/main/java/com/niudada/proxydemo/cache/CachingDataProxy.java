package com.niudada.proxydemo.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CachingDataProxy implements DataService{

    private final DataService realService;
    private final Map<String, String> cache = new HashMap<>();

    public CachingDataProxy(DataService realService) {
        this.realService = realService;
    }

    @Override
    public String fetchData(String query) {
        if (cache.containsKey(query)) {
            log.info("从缓存中获取数据：{}", query);
            return cache.get(query);
        }
        String result = realService.fetchData(query);
        cache.put(query, result);
        log.info("将结果缓存：{}", query);
        return result;
    }
}
