package com.Endortech.Discord.GenshinHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public class WebScraper {

    private static final String genUrl = "https://genshin-impact.fandom.com";
    private static final String url = "https://genshin-impact.fandom.com/wiki/Wishes";



    public List<ScrapperHelper> upcomingBanner() {
        try {

            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements body = doc.select("#mw-content-text > div.mw-parser-output > table:nth-child(25) > tbody > tr:nth-child(3) > td:nth-child(1) > a");
            String urlConst = (genUrl + body.attr("href"));
            Document doc2 = Jsoup.connect("https://genshin-impact.fandom.com/wiki/Reign_of_Serenity/2021-09-01").timeout(6000).get();

            Elements charTable = doc2.select("#mw-content-text > div.mw-parser-output > table:nth-child(17) > tbody > tr:nth-child(2) > td");
            List<String> names = new ArrayList<String>();
            List<String> images = new ArrayList<String>();
            for(Element e : charTable.select("div")){
                String name = e.select("div.card_text").select("span.card_font").text();
                String image = e.select("div.card_image").select("a > img").attr("data-src");
                images.add(image);
                names.add(name);
            }


            names.removeAll(Arrays.asList("Unknown Character", null));
            names.removeAll(Arrays.asList(" ", null));
            names.removeAll(Arrays.asList("", null));
            names = names.stream().distinct().collect(Collectors.toList());
            images.removeAll(Arrays.asList(" ", null));
            images.removeAll(Arrays.asList("", null));
            images = images.stream().distinct().collect(Collectors.toList());
            for(int i=0; i < images.size(); i++){
                boolean isFound = images.get(i).contains("Unknown");
                if(isFound){
                    images.remove(i);
                }
            }
            List<ScrapperHelper> shl = new ArrayList<>();
            for(int i=0; i < names.size(); i++){
                ScrapperHelper sh = new ScrapperHelper();
                sh.setName(names.get(i));
                sh.setImage(images.get(i));
                shl.add(sh);
            }

            return shl;

        }catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
