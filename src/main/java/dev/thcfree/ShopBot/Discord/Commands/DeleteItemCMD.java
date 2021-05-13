package dev.thcfree.ShopBot.Discord.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Item;
import dev.thcfree.ShopBot.ItemManager;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;

public class DeleteItemCMD extends Command {
    public DeleteItemCMD() {
        this.name = "deleteitem";
        this.requiredRole = Constants.ShopRole;
        this.aliases = new String[]{"di","deli"};
    }
    @Override
    protected void execute(CommandEvent event) {
        if (event.getGuild().getId().equals(Constants.GUID)) {
            Item item = ItemManager.getItemById(event.getArgs());
            if (item == null) {
                event.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("The requested item doesn't exist in the shop database").queue());
            } else {
                ItemManager.delItem(item);
                Objects.requireNonNull(event.getGuild().getTextChannelById(item.getChannelId())).retrieveMessageById(item.getMsgId()).queue(msg -> msg.delete().queue());
            }
            event.getMessage().delete().queue();
        }
    }
}
