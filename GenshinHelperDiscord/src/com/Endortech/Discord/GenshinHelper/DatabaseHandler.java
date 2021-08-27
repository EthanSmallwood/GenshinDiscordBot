package com.Endortech.Discord.GenshinHelper;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private final File currentDir = new File("");
    private final String directory = (currentDir.getAbsolutePath()+ "\\src\\com\\Endortech\\Discord\\GenshinHelper\\genshinBotDatabase\\CharacterDB.db");

    public List<DatabaseObjectOutputCharacters> dbToObjChar(){

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:"+directory);//connects to the database
            Statement stmt = con.createStatement();
            List<DatabaseObjectOutputCharacters> output = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterInfo"); //gets result set from the database
            while (rs.next()) { //converts database to object
                //takes the database private variables and assigns the database content to it
                DatabaseObjectOutputCharacters dbo = new DatabaseObjectOutputCharacters();
                dbo.setName(rs.getString("Name"));
                dbo.setElement(rs.getString("Element"));
                dbo.setRarity(rs.getInt("Rarity"));
                dbo.setConstellation(rs.getString("Constellation"));
                dbo.setWeapon(rs.getString("Weapon"));
                dbo.setImage(rs.getString("Image"));
                dbo.setBirthday(rs.getString("Birthday"));
                //adds object to array
                output.add(dbo);
            }
            stmt.close();//closes connections
            con.close();

            return output;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

    }


    public List<DatabaseObjectCharacterConst> dbToObjConst(){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:" + directory);//connects to the database
            Statement stmt = con.createStatement();
            List<DatabaseObjectCharacterConst> output = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CharacterConstellation");
            while (rs.next()) {
                DatabaseObjectCharacterConst doc = new DatabaseObjectCharacterConst();
                doc.setName(rs.getString("Name"));
                doc.setConstNameOne(rs.getString("ConstNameOne"));
                doc.setConstNameTwo(rs.getString("ConstNameTwo"));
                doc.setConstNameThree(rs.getString("ConstNameThree"));
                doc.setConstNameFour(rs.getString("ConstNameFour"));
                doc.setConstNameFive(rs.getString("ConstNameFive"));
                doc.setConstNameSix(rs.getString("ConstNameSix"));
                doc.setConstContentOne(rs.getString("ConstOne"));
                doc.setConstContentTwo(rs.getString("ConstTwo"));
                doc.setConstContentThree(rs.getString("ConstThree"));
                doc.setConstContentFour(rs.getString("ConstFour"));
                doc.setConstContentFive(rs.getString("ConstFive"));
                doc.setConstContentSix(rs.getString("ConstSix"));
                doc.setConstName(rs.getString("ConstName"));
                doc.setImage(rs.getString("Image"));
                output.add(doc);
            }
            stmt.close();//closes connections
            con.close();
            return output;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


}
