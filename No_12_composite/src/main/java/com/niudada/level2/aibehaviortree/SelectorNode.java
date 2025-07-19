package com.niudada.level2.aibehaviortree;

import com.niudada.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 选择节点
 */
@Slf4j
public class SelectorNode extends BehaviorNode {
    private final List<BehaviorNode> children;

    public SelectorNode(List<BehaviorNode> children) {
        this.children = children;
    }

    @Override
    public boolean execute(String indent) {
        System.out.println(indent + "开始选择行为:");
        for (BehaviorNode node : children) {
            if (node.execute(indent + "  ")) {
                System.out.println(indent + "选择成功 " + GlobalConstants.SUCEESS);
                return true;
            }
        }
        System.out.println(indent + "选择失败 " + GlobalConstants.FAIL);
        return false;
    }
}
