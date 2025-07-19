package com.niudada.level1.permissionmenu;

import com.niudada.constants.GlobalConstants;

public class MenuItem extends MenuComponent {

    public MenuItem(String name, String permission) {
        super(name, permission);
    }

    @Override
    public void print(String role, String indent) {
        if (hasPermission(role)) {
            System.out.println(indent + GlobalConstants.MENU_ICON + "菜单项：" + name);
        }
    }
}
