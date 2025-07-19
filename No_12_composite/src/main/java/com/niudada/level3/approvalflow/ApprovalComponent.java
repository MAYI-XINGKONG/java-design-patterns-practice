package com.niudada.level3.approvalflow;

import java.util.List;

public abstract class ApprovalComponent {
    protected String name;

    public ApprovalComponent(String name) {
        this.name = name;
    }

    public abstract void print(String indent);

    public abstract boolean approve(ApprovalContext context, String indent);

    public ApprovalComponent add(ApprovalComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(ApprovalComponent component) {
        throw new UnsupportedOperationException("不支持移除操作");
    }

    public List<ApprovalComponent> getChildren() {
        throw new UnsupportedOperationException("无子节点");
    }
}
