package com.niudada.level2.aibehaviortree;

import com.niudada.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 序列节点
 */
@Slf4j
public class SequenceNode extends BehaviorNode {
    private final List<BehaviorNode> children;

    public SequenceNode(List<BehaviorNode> children) {
        this.children = children;
    }

    @Override
    public boolean execute(String indent) {
        System.out.println(indent + "开始序列行为:");
        for (BehaviorNode node : children) {
            if (!node.execute(indent + "  ")) {
                System.out.println(indent + "序列中断!");
                return false;
            }
        }
        System.out.println(indent + "序列完成 " + GlobalConstants.SUCEESS);
        return true;
    }
}
