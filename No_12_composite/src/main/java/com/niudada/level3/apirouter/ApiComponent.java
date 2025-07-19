package com.niudada.level3.apirouter;

import java.util.List;

public abstract class ApiComponent {
    protected String name;

    public ApiComponent(String name) {
        this.name = name;
    }

    public abstract void print(String indent);

    public abstract String getFullPath();

    public ApiComponent add(ApiComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(ApiComponent component) {
        throw new UnsupportedOperationException("不支持移除操作");
    }

    public List<ApiComponent> getChildren() {
        throw new UnsupportedOperationException("无子节点");
    }

    public boolean match(String path) {
        return getFullPath().equals(path);
    }
}
