package com.niudada.level2.multilevelmenu;

public class MenuBuilder {
    public static MenuGroup buildSampleMenuStructure() {
        return new MenuGroup("主菜单", null)
                .add(new MenuItem("首页", "/home", null))
                .add(new MenuItem("仪表盘", "/dashboard", null))
                .add(new MenuGroup("用户管理", "*")
                        .add(new MenuItem("用户列表", "/user/list", "*"))
                        .add(new MenuItem("用户详情", "/user/detail", "*")))
                .add(new MenuGroup("内容管理", "editor")
                        .add(new MenuItem("文章列表", "/article/list", "editor"))
                        .add(new MenuItem("发布新文章", "/article/new", "editor")))
                .add(new MenuItem("设置", "/settings", "*"));
    }
}
