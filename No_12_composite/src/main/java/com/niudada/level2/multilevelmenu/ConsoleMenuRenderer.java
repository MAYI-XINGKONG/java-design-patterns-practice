package com.niudada.level2.multilevelmenu;

public class ConsoleMenuRenderer implements MenuRenderer {
    private final String role;

    public ConsoleMenuRenderer(String role) {
        this.role = role;
    }

    @Override
    public String getRole() {
        return role;
    }
}
