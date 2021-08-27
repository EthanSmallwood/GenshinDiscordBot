package com.Endortech.Discord.GenshinHelper;

public class DatabaseObjectOutput {
    private String name;
    private String element;
    private int rarity;
    private String constellation;
    private String weapon;
    private String image;


    public void setName(String name) {
        this.name = name;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public String getRarity() {
        StringBuilder temp = new StringBuilder();
        temp.append(":star:".repeat(Math.max(0, this.rarity)));
        return temp.toString();
    }

    public String getConstellation() {
        return constellation;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getImage() {
        return image;
    }


}
