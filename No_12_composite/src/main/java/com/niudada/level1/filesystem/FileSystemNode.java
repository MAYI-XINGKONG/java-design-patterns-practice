package com.niudada.level1.filesystem;

import java.util.List;

public abstract class FileSystemNode {
    protected String name;

    public FileSystemNode(String name) {
        this.name = name;
    }

    /**
     * @param indent 缩进效果，仅为展示结构
     */
    public abstract void print(String indent);

    public FileSystemNode add(FileSystemNode child) {
        throw new UnsupportedOperationException("不能添加到当前节点");
    }

    public void remove(FileSystemNode child) {
        throw new UnsupportedOperationException("不能从当前节点移除");
    }

    public List<FileSystemNode> getChildren() {
        throw new UnsupportedOperationException("当前节点没有子节点");
    }

    public boolean isDirectory() {
        return false;
    }
}
