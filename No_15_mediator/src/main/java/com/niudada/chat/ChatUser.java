package com.niudada.chat;

public class ChatUser extends User {
    public ChatUser(ChatRoomMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " 发送消息:\" " + message + "\"");
    }

    @Override
    public void receive(String senderName, String message) {
        System.out.println(this.name + " 收到[ " + senderName + " ]的消息: \"" + message + "\"");
    }
}
