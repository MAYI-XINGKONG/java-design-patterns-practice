package com.niudada.chat;

import org.junit.Test;

public class ChatTest {

    @Test
    public void test() {
        // 聊天室就相当于中介
        ChatRoom chatRoom = new ChatRoom();

        // 创建用户
        ChatUser ts = new ChatUser(chatRoom, "唐三藏");
        ChatUser wk = new ChatUser(chatRoom, "孙悟空");
        ChatUser bj = new ChatUser(chatRoom, "猪八戒");

        // 添加用户
        chatRoom.addUser(ts);
        chatRoom.addUser(wk);
        chatRoom.addUser(bj);

        // 发送消息
        ts.send("大家好，我是唐三藏");
        wk.send("大家好，我是孙悟空");
        bj.send("大家好，我是猪八戒");
    }
}
