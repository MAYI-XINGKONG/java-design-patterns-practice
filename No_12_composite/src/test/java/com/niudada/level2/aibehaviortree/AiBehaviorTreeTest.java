package com.niudada.level2.aibehaviortree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 行为树测试：对玩家是否在视野内进行选择判断，如果玩家在视野内则发出攻击序列，否则巡逻并返回起点
 */
@Slf4j
public class AiBehaviorTreeTest {

    /**
     * 构建一个简单的行为树(攻击玩家成功案例)
     */
    @Test
    public void successTest() {
        BehaviorNode checkVision = new ActionNode("检查玩家是否在视野内", true);
        BehaviorNode attackPlayer = new ActionNode("攻击玩家", true);
        BehaviorNode patrolAndReturn = new SequenceNode(List.of(
                new ActionNode("巡逻", true),
                new ActionNode("回到起点", true)
        ));

        // 构建一个 Sequence 作为 Selector 的第一个子节点
        BehaviorNode attackSequence = new SequenceNode(List.of(checkVision, attackPlayer));

        // 构建最终行为树
        BehaviorNode behaviorTreeRoot = new SelectorNode(List.of(
                attackSequence,
                patrolAndReturn
        ));

        var tree = new BehaviorTree(behaviorTreeRoot);
        tree.run();

    }

    /**
     * 构建一个简单的行为树(攻击玩家失败案例)
     */
    @Test
    public void failureTest() {
        BehaviorNode checkVision = new ActionNode("检查玩家是否在视野内", false);
        BehaviorNode attackPlayer = new ActionNode("攻击玩家", true);
        BehaviorNode patrolAndReturn = new SequenceNode(List.of(
                new ActionNode("巡逻", true),
                new ActionNode("回到起点", true)
        ));

        BehaviorNode attackSequence = new SequenceNode(List.of(checkVision, attackPlayer));

        BehaviorNode behaviorTreeRoot = new SelectorNode(List.of(
                attackSequence,
                patrolAndReturn
        ));

        var tree = new BehaviorTree(behaviorTreeRoot);
        tree.run();
    }

    /**
     * 好玩的：构建一个行为树，模拟一个无限循环的场景(👹小怪视角)
     * “正常程序这样写还是有问题的，请勿模仿，仅作练习”
     */
    @Test
    public void infiniteLoopTest() {
        // 构建调度器
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            // 设置守护线程
            t.setDaemon(true);
            return t;
        });

        Random random = new Random();

        scheduler.scheduleAtFixedRate(() -> {
            try {
                boolean inVision = random.nextBoolean();
                log.info("随机生成状态: 玩家是否在视野内? {}", inVision ? "是" : "否");

                BehaviorNode checkVision = new ActionNode("检查玩家是否在视野内", inVision);
                BehaviorNode attackPlayer = new ActionNode("攻击玩家", true);

                BehaviorNode patrol = new ActionNode("巡逻", true);
                BehaviorNode returnHome = new ActionNode("回到起点", true);

                // 构建巡逻序列：巡逻 -> 回到起点
                BehaviorNode patrolSequence = new SequenceNode(List.of(patrol, returnHome));

                // 构建选择器：先判断能否攻击，失败则巡逻
                BehaviorNode behaviorTreeRoot = new SelectorNode(List.of(
                        new SequenceNode(List.of(checkVision, attackPlayer)),
                        patrolSequence
                ));

                behaviorTreeRoot.execute("");

                System.out.println("------------------------------");
            } catch (Exception e) {
                System.err.println("执行任务时发生异常: " + e.getMessage());
            }
        }, 0, 3, TimeUnit.SECONDS);

        // 主线程休眠一段时间，让守护线程有机会执行
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scheduler.shutdown();
        }

    }

}
