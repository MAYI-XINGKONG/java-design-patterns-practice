package com.niudada.level2.multilevelmenu;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MultiLevelMenuTest {

    @Test
    public void test() {
        MenuComponent menu = MenuBuilder.buildSampleMenuStructure();

        log.info("普通用户访问:");
        menu.render(new ConsoleMenuRenderer("user"), "");

        log.info("管理员访问:");
        menu.render(new ConsoleMenuRenderer("*"), "");

        log.info("编辑者访问:");
        menu.render(new ConsoleMenuRenderer("editor"), "");
    }

}
