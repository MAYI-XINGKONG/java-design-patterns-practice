package com.niudada.level3.apirouter;

import com.niudada.constants.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class ApiGroup extends ApiComponent implements ApiComponentWithParentPath{
    private final List<ApiComponent> children = new ArrayList<>();
    private String fullPath;

    public ApiGroup(String name) {
        super(name);
        this.fullPath = "";
    }

    @Override
    public void setParentPath(String parentPath) {
        for (ApiComponent child : children) {
            if (child instanceof ApiComponentWithParentPath ccp) {
                ccp.setParentPath(parentPath);
            }
        }
    }

    @Override
    public String getFullPath() {
        return "";
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.MENU_ICON_GROUP + " " + name);
        for (ApiComponent component : children) {
            component.print(indent + "  ");
        }
    }

    @Override
    public ApiComponent add(ApiComponent component) {
        if (component instanceof ApiComponentWithParentPath ccp) {
            ccp.setParentPath(this.fullPath);
        }
        children.add(component);
        return this;
    }

    @Override
    public void remove(ApiComponent component) {
        children.remove(component);
    }

    @Override
    public List<ApiComponent> getChildren() {
        return children;
    }
}
