package com.example.spacetrader.entity;

import android.graphics.Color;

/**
 * The ResourceType enum represents the environment of a planet. Each type determines
 * the amount and types of goods that can be sold at the planet and their prices.
 */
public enum ResourceType {
    NO_SPECIAL_RESOURCES("No Special Resources", Color.rgb(168, 31, 236)), MINERAL_RICH("Mineral Rich", Color.rgb(102,0,0)),
    MINERAL_POOR("Mineral Poor", Color.rgb(255,255,153)), DESERT("Desert", Color.rgb(204,136,0)), LOTS_OF_WATER("Lots of Water", Color.BLUE),
    RICH_SOIL("Rich Soil", Color.rgb(0,128,0)), POOR_SOIL("Poor Soil", Color.rgb(235,162,17)), RICH_FAUNA("Rich Fauna", Color.rgb(0,153,77) ),
    LIFELESS("Lifeless", Color.rgb(154,112,71)), WEIRD_MUSHROOMS("Weird Mushrooms", Color.rgb(179, 0, 30) ), LOTS_OF_HERBS("Lots of Herbs", Color.rgb(76,230,0)),
    ARTISTIC("Artistic", Color.rgb(255,77,225)), WARLIKE("Warlike", Color.rgb(255,25,25));

    private String name;
    private int color;

    ResourceType(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString(){
        return name;
    }
}
