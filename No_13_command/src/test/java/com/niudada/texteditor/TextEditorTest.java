package com.niudada.texteditor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TextEditorTest {

    @Test
    public void test() {
        log.info("文本编辑器命令模式演示");

        TextEditor textEditor = new TextEditor();
        TextEditorInvoker invoker = new TextEditorInvoker();

        // 插入文本
        log.info("--- 插入文本 ---");
        invoker.executeCommand(new InsertTextCommand(textEditor, "Hello "));
        invoker.executeCommand(new InsertTextCommand(textEditor, "Command "));
        invoker.executeCommand(new InsertTextCommand(textEditor, "Pattern!"));

        // 删除文本：删除 "Pattern!" 8 个字符
        log.info("--- 删除文本 ---");
        invoker.executeCommand(new DeleteTextCommand(textEditor, 8));

        // 回退
        log.info("--- 回退 ---");
        invoker.undoLastCommand();

        // 再次删除并回退：删除 "Command Pattern!" 16 个字符
        log.info("--- 再次删除文本 ---");
        invoker.executeCommand(new DeleteTextCommand(textEditor, 16));

        log.info("--- 回退 ---");
        invoker.undoLastCommand();
        log.info("最终文本内容：'{}'", textEditor.getContent());
    }

}
