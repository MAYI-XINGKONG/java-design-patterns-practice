package com.niudada.level1.permissionmenu;

import com.niudada.constants.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class MenuGroup extends MenuComponent {
    private final List<MenuComponent> chidlren = new ArrayList<>();

    public MenuGroup(String name, String permission) {
        super(name, permission);
    }

    @Override
    public MenuGroup add(MenuComponent component) {
        chidlren.add(component);
        return this;
    }

    @Override
    public void remove(MenuComponent component) {
        chidlren.remove(component);
    }

    @Override
    public List<MenuComponent> getChildren() {
        return chidlren;
    }

    @Override
    public void print(String role, String indent) {
        if (hasPermission(role)) {
            System.out.println(indent + GlobalConstants.MENU_ICON_GROUP + "菜单组：" + name);
            for (MenuComponent component : chidlren) {
                component.print(role, indent + "  "); // 递归
            }
        }
    }
}
