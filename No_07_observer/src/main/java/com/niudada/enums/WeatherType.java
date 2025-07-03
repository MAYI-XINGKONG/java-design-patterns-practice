package com.niudada.enums;

import com.niudada.constants.EmojiConstants;

public enum WeatherType {
    SUNNY(EmojiConstants.SUNNY),
    RAINY(EmojiConstants.RAINY),
    CLOUDY(EmojiConstants.CLOUDY),
    SNOWY(EmojiConstants.SNOWY);

    private final String description;

    WeatherType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
