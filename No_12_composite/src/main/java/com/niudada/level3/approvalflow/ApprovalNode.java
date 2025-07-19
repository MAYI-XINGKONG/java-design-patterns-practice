package com.niudada.level3.approvalflow;

import com.niudada.constants.GlobalConstants;
import lombok.Setter;

public class ApprovalNode extends ApprovalComponent {

    public ApprovalNode(String name) {
        super(name);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.PASS_NODE + "审批节点 [" + name + "]");
    }

    @Override
    public boolean approve(ApprovalContext context, String indent) {
        System.out.println(indent + GlobalConstants.PROCESSING + "审批节点 [" + name + "] 正在处理...");
        boolean result = context.approveBy(name);
        if (!result) {
            System.out.println(indent + GlobalConstants.FAIL_NODE + "审批节点 [" + name + "] 拒绝！");
        } else {
            System.out.println(indent + GlobalConstants.PASS_NODE + "审批节点 [" + name + "] 通过！");
        }
        return result;
    }
}
