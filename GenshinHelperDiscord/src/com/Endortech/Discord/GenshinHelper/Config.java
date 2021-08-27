package com.Endortech.Discord.GenshinHelper;

public class Config {
    //
    // add token so bot can connect
    // add activity to show on bot
    // add default prefix
    //
    private String token = "ODcxMTM2MDg3MTY1MjU1Nzcw.YQW6tA.e9SswzuLDEEhbGUiGYJDTuI_2rw";
    private String activity = "Sticky honey roast";
    private String prefix = ".";


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
