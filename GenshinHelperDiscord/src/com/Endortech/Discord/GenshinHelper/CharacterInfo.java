package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;


public class CharacterInfo {
    private  GameInfo gi = new GameInfo();



    public EmbedBuilder characters(String character){
        EmbedBuilder temp = new EmbedBuilder(); //initialised embed
        temp.setTitle(character.toUpperCase());//title is the character its returning but capitalised
        temp.setImage("attachment://Character_"+character+".jpg"); //assigns the image to the embed
        return temp;
    }

    public EmbedBuilder nextBanner(){
        EmbedBuilder temp = new EmbedBuilder();
        temp.setTitle("NEXT BANNER");
        temp.setImage("attachment://Character_"+gi.getNextBanner().toLowerCase()+".jpg");
        return temp;
    }

}
