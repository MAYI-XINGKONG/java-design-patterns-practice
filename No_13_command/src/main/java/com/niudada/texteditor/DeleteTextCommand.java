package com.niudada.texteditor;

public class DeleteTextCommand implements TextCommand {
    private TextEditor editor;
    private String deletedText;
    private int deletedLength;

    public DeleteTextCommand(TextEditor editor, int length) {
        this.editor = editor;
        this.deletedLength = length;
        // 记录要删除的文本
        int start = Math.max(0, editor.getContent().length() - length);
        this.deletedText = editor.getContent().substring(start);
    }
    @Override
    public void execute() {
        editor.deleteText(deletedLength);
    }

    @Override
    public void undo() {
        editor.addText(deletedText);
    }
}
