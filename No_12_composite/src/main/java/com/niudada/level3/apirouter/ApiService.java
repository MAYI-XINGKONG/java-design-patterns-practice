package com.niudada.level3.apirouter;

import com.niudada.constants.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class ApiService extends ApiComponent {
    private final String description;
    private final List<ApiComponent> children = new ArrayList<>();
    private final String basePath;

    public ApiService(String name, String description) {
        super(name);
        this.description = description;
        this.basePath = name;
    }

    @Override
    public String getFullPath() {
        return basePath;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.MENU_ICON + " " + name + " - " + description);
        for (ApiComponent component : children) {
            component.print(indent + "  ");
        }
    }

    @Override
    public ApiComponent add(ApiComponent component) {
        if (component instanceof ApiEndpoint endpoint) {
            ((ApiComponentWithParentPath) endpoint).setParentPath(basePath);
        } else if (component instanceof ApiGroup group) {
            group.setParentPath(basePath);
        }
        children.add(component);
        return this;
    }

    @Override
    public List<ApiComponent> getChildren() {
        return children;
    }
}
