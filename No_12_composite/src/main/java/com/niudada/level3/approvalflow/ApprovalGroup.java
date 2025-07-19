package com.niudada.level3.approvalflow;

import com.niudada.constants.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class ApprovalGroup extends ApprovalComponent {
    private final List<ApprovalComponent> children = new ArrayList<>();

    public ApprovalGroup(String name) {
        super(name);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.PASS_GROUP + "审批组 [" + name + "]");
        for (ApprovalComponent component : children) {
            component.print(indent + "  ");
        }
    }

    @Override
    public boolean approve(ApprovalContext context, String indent) {
        System.out.println(indent + GlobalConstants.PROCESSING + "审批组 [" + name + "] 开始处理...");
        for (ApprovalComponent component : children) {
            if (!component.approve(context, indent + "  ")) {
                System.out.println(indent + GlobalConstants.FAIL_GROUP + "审批组 [" + name + "] 被中断！");
                return false;
            }
        }
        System.out.println(indent + GlobalConstants.PASS_GROUP + "审批组 [" + name + "] 完成");
        return true;
    }

    @Override
    public ApprovalComponent add(ApprovalComponent component) {
        children.add(component);
        return this;
    }

    @Override
    public void remove(ApprovalComponent component) {
        children.remove(component);
    }

    @Override
    public List<ApprovalComponent> getChildren() {
        return children;
    }
}
