package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class Commands extends ListenerAdapter{

    private static Config config = new Config();
    private static DailyDomains dd = new DailyDomains();
    private static CharacterInfo ch = new CharacterInfo();
    private static GameInfo gi = new GameInfo();


    public void sendMessageDelay(User user, String content,int time) {
        user.openPrivateChannel()//starts direct message
                .flatMap(channel -> channel.sendMessage(content))//constructs message
                .queueAfter(time, TimeUnit.MINUTES);//sends message after set minutes
    }

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
                                all - Shows all characters
                                asc <Character> - Shows character Ascension Guide
                                NextBanner - Shows upcoming 5* on event banner
                                Resin <Number> - Reminds you when resin refreshes 
                                Wiki <Character> - Information about characters
                                Const <Character> - Constellations for the character
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

                    if (msg.getContentRaw().substring(1).equalsIgnoreCase("all")) {
                        MessageChannel channel = event.getChannel();
                        EmbedBuilder temp2 = new EmbedBuilder();
                        temp2.setDescription("""
                                Characters:
                                Aether
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
                                Lumine
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

                        try {
                            channel.sendMessage("Please wait getting next banner").queue();
                            channel.sendMessage(ch.nextBanner().build()).queue(); //adds file to embed
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

                    if (msg.getContentRaw().substring(1, 4).equalsIgnoreCase("asc")){
                        MessageChannel channel = event.getChannel();
                        File currentDir = new File(""); //gets folder directory
                        try {
                            channel.sendMessage("Please wait, getting "+msg.getContentRaw().substring(5)).queue();
                            File file = new File(currentDir.getAbsolutePath()+"\\src\\com\\Endortech\\Discord\\GenshinHelper\\genshinBotPhotos\\Character_" + msg.getContentRaw().substring(5) + ".jpg"); //dynamically gets photo
                            channel.sendMessage(ch.characters(msg.getContentRaw().substring(5).toLowerCase()).build()).addFile(file, "Character_" + msg.getContentRaw().substring(5) + ".jpg").queue();//adds photos to embed and outputs
                        } catch (Exception ignored) {
                            channel.sendMessage(msg.getContentRaw().substring(5)+" isn't a valid character").queue();
                        }
                    }

                    //make so you can only request one reminder
                    if(msg.getContentRaw().substring(1,6).equalsIgnoreCase("resin")){
                        MessageChannel channel = event.getChannel();
                        try{
                            //check if int is between 1 and 160
                            if(Integer.parseInt(msg.getContentRaw().substring(7) )>=1 && Integer.parseInt(msg.getContentRaw().substring(7) ) <= 160) {
                                int resinAmount = Integer.parseInt(msg.getContentRaw().substring(7));//gets int
                                int time = resinAmount * gi.getResinReset();//calculates time
                                sendMessageDelay(event.getAuthor(),"Resin has refreshed",time); //gets user and delays a message by a set time
                                channel.sendMessage("Will remind you in "+time+ " Minutes").queue();
                            }else{
                                channel.sendMessage("Please enter a valid number between 1-160").queue();
                            }

                        }catch (Exception ignored) {
                            channel.sendMessage("Please enter a valid number between 1-160").queue();
                        }


                    }

                    if(msg.getContentRaw().substring(1,5).equalsIgnoreCase("wiki")){
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(ch.characterWiki(msg.getContentRaw().substring(6).toLowerCase()).build()).queue();
                    }

                    if (msg.getContentRaw().substring(1,6).equalsIgnoreCase("const")){
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(ch.constellations(msg.getContentRaw().substring(7).toLowerCase()).build()).queue();
                    }



                }catch (Exception ignored) {}
            }
        }
    }



}


