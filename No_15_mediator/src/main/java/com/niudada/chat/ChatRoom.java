package com.niudada.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatRoomMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(User user, String message) {
        for (User u : users) {
            u.receive(user.getName(), message);
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
