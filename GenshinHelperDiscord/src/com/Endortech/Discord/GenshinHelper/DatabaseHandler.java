package com.Endortech.Discord.GenshinHelper;

import java.io.File;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private final File currentDir = new File("");
    private final String directory = (currentDir.getAbsolutePath()+ "\\src\\com\\Endortech\\Discord\\GenshinHelper\\genshinBotDatabase\\CharacterDB.db");

    public List<DatabaseObjectOutput> dbToObj(){
        try {

            try {
                Connection con = DriverManager.getConnection("jdbc:sqlite:"+directory);
                Statement stmt = con.createStatement();
                List<DatabaseObjectOutput> output = new ArrayList<DatabaseObjectOutput>();
                ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterInfo");
                while (rs.next()) {
                    DatabaseObjectOutput dbo = new DatabaseObjectOutput();
                    dbo.setName(rs.getString("Name"));
                    dbo.setElement(rs.getString("Element"));
                    dbo.setRarity(rs.getInt("Rarity"));
                    dbo.setConstellation(rs.getString("Constellation"));
                    dbo.setWeapon(rs.getString("Weapon"));
                    dbo.setImage(rs.getString("Image"));

                    output.add(dbo);
                }
                stmt.close();
                con.close();

                return output;

            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


}
