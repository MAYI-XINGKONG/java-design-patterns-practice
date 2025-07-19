package com.niudada.level1.permissionmenu;

import java.util.List;

public abstract class MenuComponent {
    protected String name;
    protected String permission;

    public MenuComponent(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract void print(String role, String indent);

    public MenuComponent add(MenuComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException("不支持移除操作");
    }

    public List<MenuComponent> getChildren() {
        throw new UnsupportedOperationException("无子菜单项");
    }

    public boolean hasPermission(String userRole) {
        // 简化处理：权限字段为空表示无需权限，所有人都可以访问
        return permission == null || permission.equals(userRole);
    }
}
