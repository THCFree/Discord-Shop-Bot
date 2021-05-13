package dev.thcfree.ShopBot.Discord.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Item;
import dev.thcfree.ShopBot.ItemManager;
import dev.thcfree.ShopBot.Utils.ConfigUtils;
import dev.thcfree.ShopBot.Utils.RandomColorUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

public class AddItemCMD extends Command {
    public AddItemCMD(){
        this.name = "additem";
        this.requiredRole = Constants.ShopRole;
        this.aliases = new String[]{"ai","addi"};
    }
    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getArgs().split("\\s+");
        if(args.length == 3){
        if(!NumberUtils.isCreatable(args[1])){
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage("The number " +  args[1] + " can't be parsed into a number, price only accepts numbers").queue();
            });
            return;
        }
        EmbedBuilder itemEmbed = new EmbedBuilder();
        itemEmbed.setTitle(args[0]);
        itemEmbed.setDescription(args[1] + Constants.Currency);
        itemEmbed.setImage(args[2]);
        itemEmbed.setColor(RandomColorUtil.getRandomColor());
        event.getChannel().sendMessage(itemEmbed.build()).queue(msg ->{
            ItemManager.addItem(new Item(args[0],Double.parseDouble(args[1]),msg.getId(),event.getChannel().getId()));
            msg.addReaction("\uD83D\uDED2").queue();
            msg.addReaction("❌").queue();
        });

        event.getMessage().delete().queue();
    }
    }
}
