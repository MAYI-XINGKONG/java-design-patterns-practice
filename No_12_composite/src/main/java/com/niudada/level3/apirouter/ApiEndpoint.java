package com.niudada.level3.apirouter;

import com.niudada.constants.GlobalConstants;

public class ApiEndpoint extends ApiComponent implements ApiComponentWithParentPath{
    private final String method;
    private final String description;
    private String fullPath;

    public ApiEndpoint(String name, String method, String description) {
        super(name);
        this.method = method;
        this.description = description;
        this.fullPath = name;
    }

    @Override
    public void setParentPath(String parentPath) {
        this.fullPath = parentPath + name;
    }

    @Override
    public String getFullPath() {
        return fullPath;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.FILE_ICON + " " + method.toUpperCase() + " " + name + " - " + description);
    }
}
