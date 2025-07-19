package com.niudada.level2.multilevelmenu;

import com.niudada.constants.GlobalConstants;

public interface MenuRenderer {
    String getRole();

    default void renderMenuItem(MenuItem item, String indent) {
        System.out.println(indent + GlobalConstants.MENU_ICON + " 菜单项: " + item.name + " -> " + item.path);
    }

    default void renderMenuGroupStart(MenuGroup group, String indent) {
        System.out.println(indent + GlobalConstants.MENU_ICON_GROUP + " 菜单组: " + group.name);
    }

    default void renderMenuGroupEnd(MenuGroup group, String indent) {
        // 默认为空，可用于 HTML 或 JSON 结尾标签
    }
}
