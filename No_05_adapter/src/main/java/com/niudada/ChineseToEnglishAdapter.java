package com.niudada;

public class ChineseToEnglishAdapter implements EnglishSpeaker{

    private final ChineseSpeaker chineseSpeaker;

    public ChineseToEnglishAdapter(ChineseSpeaker chineseSpeaker) {
        this.chineseSpeaker = chineseSpeaker;
    }

    @Override
    public void speakEnglish() {
        chineseSpeaker.speakChinese();
        System.out.println("(翻译为英文：I only speak Chinese! Who talks in English for no reason... )");
    }
}
