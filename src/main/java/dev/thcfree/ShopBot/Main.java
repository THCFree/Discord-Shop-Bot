package dev.thcfree.ShopBot;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.thcfree.ShopBot.Discord.Bot;
import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Utils.ConfigUtils;
import dev.thcfree.ShopBot.Utils.SqliteUtil;
import net.dv8tion.jda.api.JDA;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class Main {

    public static JDA jda;

    public static void main(String[] args) throws IOException {

        ConfigUtils.init();
        if (args.length >= 1 && args[0].equals("--nogui")) {
            startBot();
        } else {
            FlatDarculaLaf.install();
            UserUI ui = new UserUI();
            JFrame frame = new JFrame();
            frame.setSize(550, 350);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(ui);
            frame.setTitle("Discord Shop Bot V" + Constants.Version);
            File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("icon.png")).getFile());
            frame.setIconImage(ImageIO.read(file));
            frame.setVisible(true);
        }
    }

    public static void startBot() {
        if (jda == null) {
            try {
                jda = Bot.init();
            } catch (LoginException e) {
                e.printStackTrace();
            }
            SqliteUtil.createTable();
            SqliteUtil.registerItems();
        }
    }

    public static void stopBot() {
        if (jda != null) {
            jda.shutdown();
        }

    }
}
