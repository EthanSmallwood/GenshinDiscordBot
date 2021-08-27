package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
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
        List<DatabaseObjectOutputCharacters> dbo = db.dbToObjChar();
        int index = 0;
        //check if character name is the same as one in the object and returns index value
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
        temp.addField("Birthday",dbo.get(index).getBirthday(),false);
        temp.setImage(dbo.get(index).getImage());
        Color colour = getColour(dbo.get(index).getElement());
        temp.setColor(colour);
        return temp;
    }

    private Color getColour(String element) {
        return switch (element.toLowerCase()) {
            case ("cryo") -> Color.cyan;
            case ("anemo") -> Color.green;
            case ("geo") -> Color.yellow;
            case ("pyro") -> Color.red;
            case ("hydro") -> Color.blue;
            case ("electro") -> Color.magenta;
            default -> Color.white;
        };

    }


    public EmbedBuilder constellations(String character){
        List<DatabaseObjectCharacterConst> doc = db.dbToObjConst();
        int index = 0;
        for(int i = 0; i < doc.size(); i++){
            if(doc.get(i).getName().equalsIgnoreCase(character)){
                index = i;
                break;
            }
        }
        EmbedBuilder temp = new EmbedBuilder();
        temp.setTitle(character.toUpperCase());
        temp.addField("Constellations", "\n"+doc.get(index).getConstName(),false);
        temp.addField("","",false);
        temp.addField("Dew-Drinker", "Level: 1\n"+doc.get(index).getConstContentOne(),true);
        temp.addField("The Auspicious", "Level: 2\n"+doc.get(index).getConstContentTwo(),true);
        temp.addField("Cloud-Strider", "Level: 3\n"+doc.get(index).getConstContentThree(),true);
        temp.addField("Westward Sojourn0","Level: 4\n"+doc.get(index).getConstContentFour(),true);
        temp.addField("The Merciful", "Level: 5\n"+doc.get(index).getConstContentFive(),true);
        temp.addField("The Clement", "Level: 6\n"+doc.get(index).getConstContentSix(),true);
        temp.setThumbnail(doc.get(index).getImage());
        return temp;
    }

}
