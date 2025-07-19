package com.niudada.level1.filesystem;

import com.niudada.constants.GlobalConstants;

public class File extends FileSystemNode{
    public File(String name) {
        super(name);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.FILE_ICON + "文件：" + name);
    }
}
