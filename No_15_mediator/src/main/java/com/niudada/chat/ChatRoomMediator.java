package com.niudada.chat;

public interface ChatRoomMediator {
    void sendMessage(User user, String message);
    void addUser(User user);
}
