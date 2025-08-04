package com.niudada.texteditor;

public class InsertTextCommand implements TextCommand {
    private TextEditor editor;
    private String text;

    public InsertTextCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.addText(text);
    }

    @Override
    public void undo() {
        editor.deleteText(text.length());
    }
}
