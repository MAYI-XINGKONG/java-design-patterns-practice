package com.niudada.chat;

import lombok.Getter;

public abstract class User {
    protected ChatRoomMediator mediator;

    @Getter
    protected String name;

    public abstract void send(String message);
    public abstract void receive(String senderName, String message);

    public User(ChatRoomMediator mediator, String name)
    {
        this.mediator = mediator;
        this.name = name;
    }
}
