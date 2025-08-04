package com.niudada.texteditor;

public class TextEditor {
    private StringBuffer content;

    public TextEditor() {
        this.content = new StringBuffer();
    }

    public void addText(String text) {
        content.append(text);
        System.out.println("添加文本：'" + text + "'");
        System.out.println("当前内容：'" + content.toString() + "'");
    }

    public void deleteText(int length) {
        if (content.length() >= length) {
            String deleted = content.substring(content.length() - length);
            content.delete(content.length() - length, content.length());
            System.out.println("删除文本：'" + deleted + "'");
        } else {
            String deleted = content.toString();
            content.delete(0, content.length());
            System.out.println("删除文本：'" + deleted + "'");
        }
        System.out.println("当前内容：'" + content.toString() + "'");
    }

    public String getContent() {
        return content.toString();
    }
}
