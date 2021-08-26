package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;


public class Commands extends ListenerAdapter{

    public static Config config = new Config();
    public static DailyDomains dd = new DailyDomains();
    public static CharacterInfo ch = new CharacterInfo();
    public static GameInfo gi = new GameInfo();

@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!(event.getAuthor().isBot())){//checks if message is  bot or user
            Message msg = event.getMessage();
            String temp = Character.toString(msg.getContentRaw().charAt(0));
            if (temp.equals(config.getPrefix())) {
                try {

                    if (msg.getContentRaw().substring(1).equalsIgnoreCase("help")) {// checks after prefix for help
                        MessageChannel channel = event.getChannel(); //gets the channel the command was sent in
                        EmbedBuilder help = new EmbedBuilder();// initialises embed
                        help.setTitle("Bot Help");//sets title of embed
                        help.setDescription("""
                                Help - Shows available commands
                                Domains - Shows todays domains
                                Domains <Day> - Shows that day domains
                                Ping - Pings the bot
                                Prefix <?> - Sets prefix
                                Ch - Shows all characters
                                Ch <Character> - Shows character Ascension Guide
                                NextBanner - Shows upcoming 5* on event banner
                                """);//embed content
                        help.addField("Creator", "Endortech #0858", true);// shows creator part
                        help.setColor(Color.red); // sets embed colour
                        channel.sendMessage(help.build()).queue(); // sends embed

                    }

                    if (msg.getContentRaw().substring(1).equalsIgnoreCase("ping")) {
                        MessageChannel channel = event.getChannel();
                        long time = System.currentTimeMillis(); //gets time in ms
                        channel.sendMessage("Pong!") /* => RestAction<Message> */
                                .queue(response /* => Message */ -> {
                                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue(); // then takes time away from current time to get the time taken
                                });
                    }

                    if (msg.getContentRaw().substring(1).equalsIgnoreCase("ch")) {
                        MessageChannel channel = event.getChannel();
                        EmbedBuilder temp2 = new EmbedBuilder();
                        temp2.setDescription("""
                                Characters:
                                Albedo
                                Aloy
                                Amber
                                AnemoTraveler
                                Ayaka
                                Babara
                                Barbara
                                Beidou
                                Bennett
                                Chongyun
                                Diluc
                                Diona
                                Eula
                                Fischl
                                Ganyu
                                HuTao
                                Jean
                                Kaeya
                                Kazuha
                                Keqing
                                Klee
                                Kokomi
                                Lisa
                                Mona
                                Ningguang
                                Noelle
                                Qiqi
                                Raiden
                                Razor
                                Rosaria
                                Sara
                                Sayu
                                Sucrose
                                Tartaglia
                                Venti
                                Xiangling
                                Xiao
                                Xingqiu
                                Xinyan
                                Yanfei
                                Yoimiya
                                Zhongli
                                """);
                        channel.sendMessage(temp2.build()).queue();

                    }

                    if (msg.getContentRaw().substring(1).equalsIgnoreCase("nextbanner")){
                        MessageChannel channel = event.getChannel();
                        File currentDir = new File(""); //gets project directory
                        try {
                            channel.sendMessage("Please wait getting next banner").queue();
                            File file = new File(currentDir.getAbsolutePath()+"\\src\\com\\Endortech\\Discord\\GenshinHelper\\genshinBotPhotos\\Character_" + gi.getNextBanner().toLowerCase()  + ".jpg"); // creates the file directory for the photo

                            channel.sendMessage(ch.nextBanner().build()).addFile(file, "Character_" + gi.getNextBanner().toLowerCase() + ".jpg").queue(); //adds file to embed
                        } catch (Exception ignored) {
                        }
                    }

                    if (msg.getContentRaw().substring(1, 8).equalsIgnoreCase("domains")) {
                        MessageChannel channel = event.getChannel();
                        try {
                            channel.sendMessage(dd.todaysDomain(msg.getContentRaw().substring(9)).build()).queue();
                        } catch (Exception e) {
                            channel.sendMessage(dd.todaysDomain(dd.getTodaysDay()).build()).queue();
                        }
                    }

                    if (msg.getContentRaw().substring(1, 7).equalsIgnoreCase("prefix")) {
                        MessageChannel channel = event.getChannel();
                        config.setPrefix(Character.toString(msg.getContentRaw().charAt(8))); //gets the character at index 8
                        channel.sendMessage("Prefix changed to "+msg.getContentRaw().charAt(8)).queue(); //shows what the prefix now is
                    }

                    if (msg.getContentRaw().substring(1, 3).equalsIgnoreCase("ch")){
                        MessageChannel channel = event.getChannel();
                        File currentDir = new File("");
                        try {
                            channel.sendMessage("Please wait, getting "+msg.getContentRaw().substring(4)).queue();
                            File file = new File(currentDir.getAbsolutePath()+"\\src\\com\\Endortech\\Discord\\GenshinHelper\\genshinBotPhotos\\Character_" + msg.getContentRaw().substring(4) + ".jpg");

                            channel.sendMessage(ch.characters(msg.getContentRaw().substring(4).toLowerCase()).build()).addFile(file, "Character_" + msg.getContentRaw().substring(4) + ".jpg").queue();
                        } catch (Exception ignored) {
                            channel.sendMessage(msg.getContentRaw().substring(4)+" isn't a valid character").queue();
                        }
                    }




                }catch (Exception ignored) {}
            }
        }
    }
}


