package com.niudada.level1.permissionmenu;

public class MenuService {
    public static MenuComponent buildMenuStructure() {
        MenuItem indexMenu = new MenuItem("首页", null);

        MenuGroup userMenu = new MenuGroup("用户管理", "admin")
                .add(new MenuItem("用户列表", "admin"))
                .add(new MenuItem("用户详情", "admin"));

        MenuGroup roleMenu = new MenuGroup("角色管理", "admin")
                .add(new MenuItem("角色列表", "admin"))
                .add(new MenuItem("权限分配", "admin"));

        return new MenuGroup("菜单组", null)
                .add(indexMenu)
                .add(userMenu)
                .add(roleMenu);
    }

    public static void renderMenu(MenuComponent menu, String role) {
        if (menu.hasPermission(role)) {
            menu.print(role, "");
        }
    }
}
