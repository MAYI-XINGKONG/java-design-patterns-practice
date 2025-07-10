package com.niudada.proxydemo.lazy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealImage implements Image{
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(filename);
    }

    private void loadFromDisk(String filename) {
        // 模拟从磁盘加载图像的耗时操作
        try {
            log.info("正在加载图片 " + filename);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void display() {
        log.info("显示图片 " + filename);
    }
}
