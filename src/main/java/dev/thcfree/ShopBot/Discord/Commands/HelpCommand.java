package dev.thcfree.ShopBot.Discord.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Utils.RandomColorUtil;
import net.dv8tion.jda.api.EmbedBuilder;

public class HelpCommand extends Command {
    public HelpCommand(){
        this.name = "help";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getAuthor().openPrivateChannel().queue(privateChannel -> {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Help Page:");
            embedBuilder.addField("Additem","Adds a item to the shop, \nUsage: " + Constants.Prefix + "additem <name> <price> <image url> \nAliases: ai, addi",false);
            embedBuilder.addField("AdditemDesc","Adds a item to the shop with a description, \nUsage: " + Constants.Prefix + "additemdesc <name>;<price>;<description>;<image url> \nAliases: aid, addid",false);
            embedBuilder.addField("DeleteItem","Deletes a item from the shop, \nUsage: " + Constants.Prefix + "deleteitem <message id> \nAliases: di deli",false);
            embedBuilder.setColor(RandomColorUtil.getRandomColor());
            embedBuilder.setFooter("https://github.com/THCFree/Discord-Shop-Bot");
            privateChannel.sendMessage(embedBuilder.build()).queue();
            event.getMessage().delete().queue();

        });
    }
}
