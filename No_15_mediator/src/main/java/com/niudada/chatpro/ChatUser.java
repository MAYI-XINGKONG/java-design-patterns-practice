package com.niudada.chatpro;

public class ChatUser extends User {
    private ChatWindow window;

    public ChatUser(ChatRoomMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        if (message.equals("/quit")) {
            mediator.removeUser(this);
            if (window != null) {
                window.close();
            }
            return;
        }
        // 发送给其他用户
        mediator.sendMessage(this, message);
    }

    @Override
    public void receive(String sender, String message) {
        if (window != null) {
            window.displayMessage(sender, message);
        }
    }

    public void setWindow(ChatWindow window) {
        this.window = window;
    }
}
