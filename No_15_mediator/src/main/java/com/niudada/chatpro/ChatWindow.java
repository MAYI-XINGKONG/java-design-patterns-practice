package com.niudada.chatpro;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChatWindow {
    private Stage stage;
    private VBox chatArea;
    private ScrollPane scrollPane;
    private TextField messageField;
    private Button sendButton;
    private ChatUser user;

    public ChatWindow(ChatUser user) {
        this.user = user;
        user.setWindow(this);
        initUI();
    }

    private void initUI() {
        stage = new Stage();
        stage.setTitle("聊天室 - " + user.getName());

        // 聊天显示区域
        chatArea = new VBox(5);
        chatArea.setPadding(new Insets(10));

        scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);
        // 移除绑定，改为手动控制滚动

        // 输入区域
        messageField = new TextField();
        messageField.setPromptText("输入消息...");

        sendButton = new Button("发送");

        // 布局
        HBox inputBox = new HBox(10, messageField, sendButton);
        inputBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(inputBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);

        // 事件处理
        sendButton.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage());

        // 窗口关闭事件
        stage.setOnCloseRequest(this::handleClose);
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            user.send(message);
            messageField.clear();
        }
    }

    private void handleClose(WindowEvent event) {
        user.send("/quit");
    }

    public void displayMessage(String sender, String message) {
        Platform.runLater(() -> {
            boolean isOwnMessage = sender.equals(user.getName());
            String displayMessage = message;
            String displaySender = sender;

            // 处理系统消息
            if ("SYSTEM".equals(sender)) {
                displayMessage = message;
                displaySender = "SYSTEM";
            }

            MessageBubble bubble = new MessageBubble(displaySender, displayMessage, isOwnMessage);
            chatArea.getChildren().add(bubble);

            // 自动滚动到底部
            scrollPane.setVvalue(1.0);
        });
    }

    public void show() {
        Platform.runLater(() -> {
            stage.show();
            messageField.requestFocus();
        });
    }

    public void close() {
        Platform.runLater(() -> {
            stage.close();
        });
    }
}
