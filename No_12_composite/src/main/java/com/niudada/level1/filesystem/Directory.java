package com.niudada.level1.filesystem;

import com.niudada.constants.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemNode {

    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public Directory add(FileSystemNode node) {
        children.add(node);
        return this;
    }

    @Override
    public void remove(FileSystemNode child) {
        children.remove(child);
    }

    @Override
    public List<FileSystemNode> getChildren() {
        return children;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + GlobalConstants.DIRECTORY_ICON + "目录：" + name);
        for (FileSystemNode node : children) {
            node.print(indent + "  "); // 每层递进两个空格，仅为展示结构
        }
    }
}
