package com.Endortech.Discord.GenshinHelper;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    public static Config config = new Config();

    public static void main(String[] args) throws LoginException
    {
        //initialise java discord library
        JDA jda = JDABuilder.createLight(config.getToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing(config.getActivity()))//sets activity of discord
                .build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);// discord bot online status
        jda.addEventListener(new Commands());//event listener initialise

    }



}
