package com.niudada;

import com.niudada.chatpro.ChatRoom;
import com.niudada.chatpro.ChatUser;
import com.niudada.chatpro.ChatWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 创建聊天室中介
        ChatRoom chatRoom = new ChatRoom();

        // 创建用户
        ChatUser user1 = new ChatUser(chatRoom, "用户1");
        ChatUser user2 = new ChatUser(chatRoom, "用户2");

        // 创建窗口
        ChatWindow window1 = new ChatWindow(user1);
        ChatWindow window2 = new ChatWindow(user2);

        // 添加用户到聊天室
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);

        // 显示所有窗口
        window1.show();
        window2.show();

        // 设置程序在所有窗口关闭后退出
        Platform.setImplicitExit(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}