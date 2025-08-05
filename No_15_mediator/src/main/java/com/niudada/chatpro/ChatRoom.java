package com.niudada.chatpro;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatRoomMediator {

    @Getter
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(User user, String message) {
        // 添加时间戳
        String timestamp = java.time.LocalTime.now().toString().substring(0, 8);
        String formattedMessage = "[" + timestamp + "] " + ": " + message;

        for (User u : users) {
            // 发送消息给所有用户
            u.receive(user.getName(), formattedMessage);
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        // 通知其他用户有新用户加入
        for (User u : users) {
            if (u != user) {
                u.receive("系统", user.getName() + " 加入了聊天室");
            }
        }
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        // 通知其他用户有用户离开
        for (User u : users) {
            u.receive("系统", user.getName() + " 离开了聊天室");
        }
    }
}
