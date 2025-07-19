package com.niudada.level2.multilevelmenu;

import java.util.List;

public abstract class MenuComponent {
    protected String name;
    protected String permission;

    public MenuComponent(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract void render(MenuRenderer renderer, String indent);

    public MenuComponent add(MenuComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException("不支持移除操作");
    }

    public List<MenuComponent> getChildren() {
        throw new UnsupportedOperationException("无子菜单项");
    }

    public boolean hasPermission(String role) {
        // 简单实现
        return permission == null || permission.equals(role) || "*".equals(role);
    }
}
