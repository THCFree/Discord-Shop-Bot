package dev.thcfree.ShopBot.Listeners;

import dev.thcfree.ShopBot.ItemManager;
import dev.thcfree.ShopBot.Utils.SqliteUtil;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageDeleteEventListener extends ListenerAdapter {
    @Override
    public void onMessageDelete(MessageDeleteEvent e){
        if(e.isFromGuild() && ItemManager.getItemById(e.getMessageId())!= null){
            SqliteUtil.delItem(e.getMessageId());
        }
    }
}
