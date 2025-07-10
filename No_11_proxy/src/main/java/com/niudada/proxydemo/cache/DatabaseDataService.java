package com.niudada.proxydemo.cache;

public class DatabaseDataService implements DataService{
    @Override
    public String fetchData(String query) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "结果：" + query.toUpperCase();
    }
}
