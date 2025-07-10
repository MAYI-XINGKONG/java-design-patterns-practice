package com.niudada.proxydemo.lazy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LazyTest {

    @Test
    public void testLazy() {
        log.info("开始测试懒加载代理模式");
        Image image1 = new ImageProxy("photo1.png");
        Image image2 = new ImageProxy("photo2.png");

        log.info("首次调用 *************** display()");
        image1.display();
        image2.display();

        log.info("再次调用 *************** display()");
        image1.display();
        image2.display();
    }
}
