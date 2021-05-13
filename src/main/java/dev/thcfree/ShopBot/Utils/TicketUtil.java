package dev.thcfree.ShopBot.Utils;

import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class TicketUtil {
    public static void createTicket(User user) {
        Guild guild = Main.jda.getGuildById(Constants.GUID);
        guild.retrieveMember(user).queue(member -> {
            for (Category category : guild.getCategories()) {
                if (category.getName().equalsIgnoreCase("TICKETS")) {
                    String formName = user.getName().replaceAll(" ", "-");
                    category.createTextChannel("Ticket-" + formName).queue(ticket -> {
                        ticket.createPermissionOverride(member).setAllow(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI).queue();
                        ticket.sendMessage(user.getAsMention()).queue();
                        if(Constants.MentionRole){
                            ticket.sendMessage(guild.getRolesByName(Constants.ShopRole,true).get(0).getAsMention()).queue();
                        }
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle(user.getName() + "'s" + " order:");
                        embed.setDescription(ShoppingCartUtil.ticketMap.get(user.getId()));
                        embed.setColor(RandomColorUtil.getRandomColor());
                        ticket.sendMessage(embed.build()).queue(msg->{
                           msg.addReaction("❌") .queue();
                        });
                    });
                }
            }
        });
    }
}
