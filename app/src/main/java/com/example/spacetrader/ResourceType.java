package com.example.spacetrader;

public enum ResourceType {
    NO_SPECIAL_RESOURCES("No Special Resources"), MINERAL_RICH("Mineral Rich"),
    MINERAL_POOR("Mineral Poor"), DESERT("Desert"), LOTS_OF_WATER("Lots of Water"),
    RICH_SOIL("Rich Soil"), POOR_SOIL("Poor Soil"), RICH_FAUNA("Rich Fauna"),
    LIFELESS("Lifeless"), WEIRD_MUSHROOMS("Weird Mushrooms"), LOTS_OF_HERBS("Lots of Herbs"),
    ARTISTIC("Artistic"), WARLIKE("Warlike");

    private String name;

    ResourceType(String name) {
        this.name = name;
    }
}
