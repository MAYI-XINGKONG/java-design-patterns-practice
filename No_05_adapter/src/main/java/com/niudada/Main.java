package com.niudada;

public class Main {
    public static void main(String[] args) {
        // 只会讲中文
        ChineseSpeaker chinesePerson = new ChineseSpeaker();
        // 经过适配器将中文翻译为英文,让他能够说英文
        EnglishSpeaker englishPerson = new ChineseToEnglishAdapter(chinesePerson);
        // 客户端真正关心的地方, 就是这个方法
        englishPerson.speakEnglish();
    }
}