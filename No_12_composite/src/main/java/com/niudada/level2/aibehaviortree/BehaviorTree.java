package com.niudada.level2.aibehaviortree;

import lombok.extern.slf4j.Slf4j;

/**
 * 行为树构建
 */
@Slf4j
public class BehaviorTree {
    private final BehaviorNode root;

    public BehaviorTree(BehaviorNode root) {
        this.root = root;
    }

    public void run() {
        System.out.println("=== 开始执行行为树 ===");
        root.execute("");
        System.out.println("=== 行为树执行完毕 ===");
    }
}
