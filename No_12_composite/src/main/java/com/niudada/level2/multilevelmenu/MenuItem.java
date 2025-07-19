package com.niudada.level2.multilevelmenu;

public class MenuItem extends MenuComponent {
    protected final String path;

    public MenuItem(String name, String path, String permission) {
        super(name, permission);
        this.path = path;
    }

    @Override
    public void render(MenuRenderer renderer, String indent) {
        if (hasPermission(renderer.getRole())) {
            renderer.renderMenuItem(this, indent);
        }
    }
}
