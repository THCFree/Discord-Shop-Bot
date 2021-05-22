package dev.thcfree.ShopBot.Listeners;


import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.ItemManager;
import dev.thcfree.ShopBot.Utils.ShoppingCartUtil;
import dev.thcfree.ShopBot.Utils.TicketUtil;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ReactionEventListener extends ListenerAdapter {
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (event.getChannelType().isGuild()) {
            if (!Objects.requireNonNull(event.getUser()).isBot()) {
                if (event.getReaction().getReactionEmote().isEmoji()) {
                    if (event.getReaction().getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDED2")) {
                        if(ItemManager.getItemById(event.getMessageId()) == null){
                            System.out.println("Couldnt get the item kek");
                            return;
                        }
                        ShoppingCartUtil.addItemToCart(event.getUserId(), ItemManager.getItemById(event.getMessageId()));
                        ShoppingCartUtil.createTicket(event.getUserId());
                        event.getReaction().removeReaction(event.getUser()).queue();
                    } else if (event.getReaction().getReactionEmote().getEmoji().equalsIgnoreCase("❌")) {
                        ShoppingCartUtil.removeItemFromCart(event.getUserId(), ItemManager.getItemById(event.getMessageId()));
                        ShoppingCartUtil.createTicket(event.getUserId());
                        event.getReaction().removeReaction(event.getUser()).queue();
                    }
                    } else {
                        event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
                    }
                if (event.getReactionEmote().getEmoji().equalsIgnoreCase("❌")) {
                    for (Category cat : event.getGuild().getCategories()) {
                        if (cat.getName().equalsIgnoreCase("Tickets")) {
                            if (cat.getTextChannels().contains(event.getTextChannel())) {
                                event.getTextChannel().delete().queue();
                            }
                        }
                    }
                }
            }
        }else if(event.getChannelType().equals(ChannelType.PRIVATE)){
            if (!event.getUser().isBot()) {
                if(event.getReaction().getReactionEmote().isEmoji()){
                    if(event.getReaction().getReactionEmote().getEmoji().equals("\uD83D\uDED2")){
                        if(ShoppingCartUtil.getTotal(event.getUser()) <= Constants.MinPurchase){
                            event.getPrivateChannel().retrieveMessageById(event.getMessageId()).queue(msg -> {
                                msg.delete().queue();
                            });
                            event.getPrivateChannel().sendMessage("Generating ticket...").queue();
                            event.getPrivateChannel().sendMessage("Your order is under the minimum required purchase of " + Constants.MinPurchase + Constants.Currency).queue();
                            ShoppingCartUtil.createTicket(event.getUserId());
                        }else {
                            TicketUtil.createTicket(event.getUser());
                            event.getPrivateChannel().retrieveMessageById(event.getMessageId()).queue(msg -> {
                                msg.delete().queue();
                            });
                            ShoppingCartUtil.deleteCart(event.getUser());
                            event.getPrivateChannel().sendMessage("Your ticket has been created!").queue();
                            event.getPrivateChannel().close().queue();
                        }
                    }else
                        if(event.getReaction().getReactionEmote().getEmoji().equals("❌")){
                        ShoppingCartUtil.deleteCart(event.getUser());
                        event.getPrivateChannel().sendMessage("Your shopping cart has been cleared").queue();
                            event.getPrivateChannel().retrieveMessageById(event.getMessageId()).queue(msg->{
                                msg.delete().queue();
                            });
                        event.getPrivateChannel().close().queue();
                    }

                }
            }
        }
    }
}
