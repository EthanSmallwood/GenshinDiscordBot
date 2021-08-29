package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;
import org.sqlite.util.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CharacterInfo {
    private  GameInfo gi = new GameInfo();
    private DatabaseHandler db = new DatabaseHandler();
    private List<DatabaseObjectOutputCharacters> dbo = db.dbToObjChar();
    private List<DatabaseObjectCharacterConst> doc = db.dbToObjConst();
    private WebScraper ws = new WebScraper();


    public EmbedBuilder characters(String character){
        EmbedBuilder temp = new EmbedBuilder(); //initialised embed
        temp.setTitle(character.toUpperCase());//title is the character its returning but capitalised
        temp.setImage("attachment://Character_"+character+".jpg"); //assigns the image to the embed
        Color colour = getColour(character);
        temp.setColor(colour);
        return temp;
    }

    public EmbedBuilder nextBanner(){
        List<ScrapperHelper> bannerList = ws.upcomingBanner();
        String tempString = "\n";
        for (ScrapperHelper scrapperHelper : bannerList) {
            tempString += scrapperHelper.getName();
            tempString += ", ";
        }
        tempString = tempString.substring(0, tempString.length() - 2);
        List<Integer> indexName = new ArrayList<>();
        for(int i = 0; i < bannerList.size(); i++) {
            String[] characterNameCheck = bannerList.get(i).getName().split(" ");
            for(int y = 0; y <characterNameCheck.length; y++) {
                for (int x = 0; x < dbo.size(); x++) {
                    if (dbo.get(x).getName().equalsIgnoreCase(characterNameCheck[y])) {
                        indexName.add(x);
                    }
                }
            }
        }

        EmbedBuilder temp = new EmbedBuilder();
        temp.setTitle("NEXT BANNER");
        temp.addField("Characters on banner",tempString,true);
        temp.setImage(dbo.get(indexName.get(0)).getImage());
        Color colour = getColour(dbo.get(indexName.get(0)).getName());
        temp.setColor(colour);
        return temp;
    }

    public EmbedBuilder characterWiki(String character) {
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
        Color colour = getColour(character);
        temp.setColor(colour);
        return temp;
    }

    private Color getColour(String character) {
        int index = 0;
        for(int i = 0; i < dbo.size(); i++){
            if(dbo.get(i).getName().equalsIgnoreCase(character)){
                index = i;
                break;
            }
        }

        return switch (dbo.get(index).getElement().toLowerCase()) {
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
        temp.addField(doc.get(index).getConstNameOne(), "Level: 1\n"+doc.get(index).getConstContentOne(),true);
        temp.addField(doc.get(index).getConstNameTwo(), "Level: 2\n"+doc.get(index).getConstContentTwo(),true);
        temp.addField(doc.get(index).getConstNameThree(), "Level: 3\n"+doc.get(index).getConstContentThree(),true);
        temp.addField(doc.get(index).getConstNameFour(),"Level: 4\n"+doc.get(index).getConstContentFour(),true);
        temp.addField(doc.get(index).getConstNameFive(), "Level: 5\n"+doc.get(index).getConstContentFive(),true);
        temp.addField(doc.get(index).getConstNameSix(), "Level: 6\n"+doc.get(index).getConstContentSix(),true);
        temp.setThumbnail(doc.get(index).getImage());
        Color colour = getColour(character);
        temp.setColor(colour);
        return temp;
    }

}
