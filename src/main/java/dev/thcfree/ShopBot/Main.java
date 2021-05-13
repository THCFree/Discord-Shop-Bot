package dev.thcfree.ShopBot;

import dev.thcfree.ShopBot.Discord.Bot;
import dev.thcfree.ShopBot.Utils.ConfigUtils;
import dev.thcfree.ShopBot.Utils.SqliteUtil;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class Main {
    public static JDA jda;
public static void main(String[] args) {

    try {
        ConfigUtils.init();
        jda = Bot.init();
        SqliteUtil.createTable();
        SqliteUtil.registerItems();
    } catch (LoginException e) {
        e.printStackTrace();
    }
}
}
