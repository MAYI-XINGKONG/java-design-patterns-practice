package com.niudada.chatpro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MessageBubble extends HBox {
    private static final int CORNER_RADIUS = 10;
    private static final int PADDING = 10;

    private Rectangle background;
    private Label messageLabel;
    private Label senderLabel;
    private boolean isOwnMessage;

    public MessageBubble(String sender, String message, boolean isOwnMessage) {
        this.isOwnMessage = isOwnMessage;
        initializeComponents(sender, message);
        layoutComponents();
    }

    private void initializeComponents(String sender, String message) {
        // 创建背景
        background = new Rectangle();
        background.setArcWidth(CORNER_RADIUS * 2);
        background.setArcHeight(CORNER_RADIUS * 2);

        // 设置背景颜色
        if (isOwnMessage) {
            background.setFill(Color.LIGHTBLUE);
        } else if ("SYSTEM".equals(sender)) {
            background.setFill(Color.LIGHTGRAY);
        } else {
            background.setFill(Color.LIGHTGREEN);
        }

        // 创建消息标签
        messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(300);

        // 创建发送者标签（仅对非自己消息显示）
        if (!isOwnMessage && !"SYSTEM".equals(sender)) {
            senderLabel = new Label(sender);
            senderLabel.setStyle("-fx-font-weight: bold;");
        }
    }

    private void layoutComponents() {
        this.setPadding(new Insets(5));

        VBox messageContainer = new VBox(5);
        messageContainer.getChildren().add(messageLabel);

        if (senderLabel != null) {
            // 发送者名字放在上面
            messageContainer.getChildren().add(0, senderLabel);
        }

        messageContainer.setPadding(new Insets(PADDING));

        if (isOwnMessage) {
            // 自己的消息靠右对齐
            this.setAlignment(Pos.CENTER_RIGHT);
            messageContainer.setAlignment(Pos.CENTER_RIGHT);
        } else {
            // 别人的消息靠左对齐
            this.setAlignment(Pos.CENTER_LEFT);
            messageContainer.setAlignment(Pos.CENTER_LEFT);
        }

        // 将背景和消息容器组合
        getChildren().addAll(messageContainer);

        // 绑定背景大小到内容大小
        messageContainer.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            background.setWidth(newVal.getWidth() + PADDING * 2);
            background.setHeight(newVal.getHeight() + PADDING * 2);
        });

        messageContainer.setClip(background);
    }
}
