package com.niudada.level2.aibehaviortree;

import com.niudada.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * 叶子节点
 */
@Slf4j
public class ActionNode extends BehaviorNode {
    private final String actionName;
    private final boolean success;

    public ActionNode(String actionName, boolean success) {
        this.actionName = actionName;
        this.success = success;
    }

    @Override
    public boolean execute(String indent) {
        System.out.print(indent + "执行动作: " + actionName + " -> ");
        if (success) {
            System.out.println(GlobalConstants.SUCEESS + " 成功!");
            return true;
        } else {
            System.out.println(GlobalConstants.FAIL + " 失败!");
            return false;
        }
    }
}
