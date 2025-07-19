package com.niudada.level1.filesystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FileSystemTest {

    @Test
    public void test() {
        // 构建系统结构
        log.info("文件系统结构");
        FileSystemNode root2 = new Directory("root")
                .add(new File("README.md"))
                .add(new Directory("etc")
                        .add(new File("hosts"))
                        .add(new File("fstab")))
                .add(new Directory("home")
                        .add(new Directory("user")
                                .add(new File("profile"))))
                .add(new Directory("var")
                        .add(new File("log.txt"))
                        .add(new File("data.log")));

//        FileSystemNode log = new File("log.txt")
//                .add(new Directory("log"));

        root2.print("");
    }
}
