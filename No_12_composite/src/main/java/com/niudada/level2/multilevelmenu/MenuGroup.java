package com.niudada.level2.multilevelmenu;

import java.util.ArrayList;
import java.util.List;

public class MenuGroup extends MenuComponent {
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuGroup(String name, String permission) {
        super(name, permission);
    }

    @Override
    public MenuGroup add(MenuComponent component) {
        children.add(component);
        return this;
    }

    @Override
    public void remove(MenuComponent component) {
        children.remove(component);
    }

    @Override
    public List<MenuComponent> getChildren() {
        return children;
    }

    @Override
    public void render(MenuRenderer renderer, String indent) {
        if (hasPermission(renderer.getRole())) {
            renderer.renderMenuGroupStart(this, indent);
            for (MenuComponent component : children) {
                component.render(renderer, indent + "  ");
            }
            renderer.renderMenuGroupEnd(this, indent);
        }
    }
}
