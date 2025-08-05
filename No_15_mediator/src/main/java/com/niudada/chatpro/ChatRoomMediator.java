package com.niudada.chatpro;

public interface ChatRoomMediator {
    void sendMessage(User user, String message);
    void addUser(User user);
    void removeUser(User user);
}
