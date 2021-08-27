package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;

import java.sql.SQLException;
import java.util.List;


public class CharacterInfo {
    private  GameInfo gi = new GameInfo();
    private DatabaseHandler db = new DatabaseHandler();


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

    public EmbedBuilder characterWiki(String character) {
        List<DatabaseObjectOutput> dbo = db.dbToObj();
        int index = 0;
        for(int i = 0; i < dbo.size(); i++){
            if(dbo.get(i).getName().equalsIgnoreCase(character)){
                index = i;
                break;
            }
        }
        EmbedBuilder temp = new EmbedBuilder();
        temp.setTitle(character.toUpperCase());
        temp.addField("Element",dbo.get(index).getElement(),false);
        temp.addField("Rarity",dbo.get(index).getRarity()+"",false);
        temp.addField("Constellation",dbo.get(index).getConstellation(),false);
        temp.addField("Weapon",dbo.get(index).getWeapon(),false);
        temp.setImage(dbo.get(index).getImage());
        return temp;
    }

}
