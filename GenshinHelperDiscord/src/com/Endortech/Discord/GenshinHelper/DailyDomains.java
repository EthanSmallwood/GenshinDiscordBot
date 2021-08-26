package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;

import java.time.LocalDate;

public class DailyDomains {

    public String getTodaysDay(){
        return LocalDate.now().getDayOfWeek().name();//gets today's date
    }

    public EmbedBuilder todaysDomain(String day){


        if(day.equalsIgnoreCase("monday") || day.equalsIgnoreCase("thursday")){
            EmbedBuilder temp = new EmbedBuilder();
            temp.setTitle((day.toUpperCase()) + " DOMAINS");
            temp.setImage("https://cdn.discordapp.com/attachments/635913556998750208/872174896422195260/unknown.png");
            return temp;
        }

        if(day.equalsIgnoreCase("tuesday") || day.equalsIgnoreCase("friday")){
            EmbedBuilder temp = new EmbedBuilder();
            temp.setTitle((day.toUpperCase()) + " DOMAINS");
            temp.setImage("https://cdn.discordapp.com/attachments/635913556998750208/872174088637005884/unknown.png");
            return temp;

        }

        if(day.equalsIgnoreCase("wednesday") || day.equalsIgnoreCase("saturday")){
            EmbedBuilder temp = new EmbedBuilder();
            temp.setTitle((day.toUpperCase()) + " DOMAINS");
            temp.setImage("https://cdn.discordapp.com/attachments/635913556998750208/872175483087912980/unknown.png");
            return temp;
        }
        return null;

    }


}
