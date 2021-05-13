package dev.thcfree.ShopBot.Discord;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import dev.thcfree.ShopBot.Discord.Commands.AddItemCMD;
import dev.thcfree.ShopBot.Discord.Commands.AddItemWDesc;
import dev.thcfree.ShopBot.Discord.Commands.DeleteItemCMD;
import dev.thcfree.ShopBot.Discord.Commands.HelpCommand;
import dev.thcfree.ShopBot.Listeners.MessageDeleteEventListener;
import dev.thcfree.ShopBot.Listeners.ReactionEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA init() throws LoginException {
        CommandClientBuilder cmdClientBuilder = new CommandClientBuilder();
        cmdClientBuilder.setOwnerId(Constants.OwnerID);
        cmdClientBuilder.setHelpWord("\u0000");
        cmdClientBuilder.setActivity(Activity.watching(Constants.Activity));
        cmdClientBuilder.setPrefix(Constants.Prefix);
        CommandClient cmdClient = cmdClientBuilder.build();
        cmdClient.addCommand(new AddItemCMD());
        cmdClient.addCommand(new DeleteItemCMD());
        cmdClient.addCommand(new AddItemWDesc());
        cmdClient.addCommand(new HelpCommand());
        return JDABuilder.createLight(Constants.Token).addEventListeners(cmdClient, new ReactionEventListener(), new MessageDeleteEventListener()).build();

    }
}
