package com.Endortech.Discord.GenshinHelper;

public class Config {

    private String token = "";
    private String activity = "Sticky honey roast";
    private String prefix = ".";


    public Config() {
    }

    public String getToken() {
        return token;
    }

    public String getActivity() {
        return activity;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


}
