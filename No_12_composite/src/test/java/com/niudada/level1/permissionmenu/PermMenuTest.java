package com.niudada.level1.permissionmenu;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class PermMenuTest {

    @Test
    public void test() {
        MenuComponent menu = MenuService.buildMenuStructure();

        log.info("普通用户访问：");
        MenuService.renderMenu(menu, "user");

        log.info("管理员访问：");
        MenuService.renderMenu(menu, "admin");
    }
}
