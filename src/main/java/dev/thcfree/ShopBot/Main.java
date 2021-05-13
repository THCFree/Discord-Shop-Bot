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
    ConfigUtils.init();
    try {
        jda = Bot.init();
    } catch (LoginException e) {
        e.printStackTrace();
    }
    SqliteUtil.createTable();
    SqliteUtil.registerItems();
}
}
