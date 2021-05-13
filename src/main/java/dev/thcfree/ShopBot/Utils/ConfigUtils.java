package dev.thcfree.ShopBot.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.thcfree.ShopBot.Discord.Constants;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigUtils {
    private static final Path configPath = Paths.get("config.json");
    private static final File configFile = configPath.toFile();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void saveConfig() throws IOException {
        JsonObject configObject = new JsonObject();
        configObject.addProperty("Token","");
        configObject.addProperty("GuildId","");
        configObject.addProperty("Currency","");
        configObject.addProperty("Prefix","!");
        configObject.addProperty("Activity","Hey im using THCFree's shop bot!");
        configObject.addProperty("OwnerId","");
        configObject.addProperty("ShopRole","");
        configObject.addProperty("MinPurchase","5");
        OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(configFile));
        fileOutputStreamWriter.write(gson.toJson(configObject));
        fileOutputStreamWriter.flush();
        fileOutputStreamWriter.close();
    }
    public static void loadConfig() throws FileNotFoundException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(configFile));
        JsonObject configObject = gson.fromJson(reader,JsonObject.class);
        Constants.Token = configObject.get("Token").getAsString();
        Constants.GUID = configObject.get("GuildId").getAsString();
        Constants.Currency = configObject.get("Currency").getAsString();
        Constants.Prefix = configObject.get("Prefix").getAsString();
        Constants.Activity = configObject.get("Activity").getAsString();
        Constants.OwnerID = configObject.get("OwnerId").getAsString();
        Constants.ShopRole = configObject.get("ShopRole").getAsString();
        Constants.MinPurchase = configObject.get("MinPurchase").getAsDouble();
    }
    public static void init(){
        if(configFile.exists()){
            try {
                loadConfig();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
