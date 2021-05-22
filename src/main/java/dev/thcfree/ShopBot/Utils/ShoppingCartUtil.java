package dev.thcfree.ShopBot.Utils;
import dev.thcfree.ShopBot.Discord.Constants;
import dev.thcfree.ShopBot.Item;
import dev.thcfree.ShopBot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartUtil {
    public static Map<String,Map<Item,Integer>> cart = new HashMap<>();
    static void createShoppingCart(String uid, Item item){
            Map<Item,Integer> items = new HashMap<>();
            items.put(item,1);
            cart.put(uid,items);
    }
    public static void addItemToCart(String uid, Item item){
        if(!cart.containsKey(uid)){
            createShoppingCart(uid, item);
        }else{
            Map<Item,Integer> items = cart.get(uid);
            if(items.containsKey(item)){
                items.replace(item,items.get(item)+1);
            }else{
                items.put(item,1);
            }
        }
    }
    public static void removeItemFromCart(String uid , Item item) {
        Map<Item, Integer> items = cart.get(uid);
        if (!cart.containsKey(uid)) {
            System.out.println("You don't have a shopping cart!");
        } else if (items.get(item) < 1) {

            items.remove(item);
        }else if (items.containsKey(item)) {
                items.replace(item, items.get(item) -1);
            }
        }
    public static Map<Item,Integer> getShoppingCart(String uid){
       return cart.get(uid);
    }

    public static Map<String,String> ticketMap = new HashMap<>();

    public static void createTicket(String UID){
        Map<Item,Integer> cart = getShoppingCart(UID);
        Guild guild = Main.jda.getGuildById(Constants.GUID);
        assert guild != null;
        guild.retrieveMemberById(UID).queue(member ->{
            member.getUser().openPrivateChannel().queue(privateChannel -> {
                StringBuilder message = new StringBuilder();
                StringBuilder finalTicket = new StringBuilder();
                double total = 0;
                for(Map.Entry<Item ,Integer> entry : cart.entrySet()){
                        message.append(entry.getKey().getName()).append(" x ").append(entry.getValue()).append(" = ").append(entry.getKey().getPrice()* entry.getValue()).append(Constants.Currency).append("\n");
                        finalTicket.append(entry.getKey().getName()).append(" x ").append(entry.getValue()).append(" = ").append(entry.getKey().getPrice()* entry.getValue()).append(Constants.Currency).append("\n");
                        total = total + ((entry.getKey().getPrice())* entry.getValue());
                }
                message.append("The total is: ").append(new DecimalFormat("#.##").format(total)).append(Constants.Currency).append("\n") ;
                message.append("--------------------------------------------------------------");
                message.append("\n");
                message.append("\n");
                message.append("React with \uD83D\uDED2 to confirm the order and create a ticket \n React with ❌ to cancel the order");
                EmbedBuilder ticket = new EmbedBuilder();
                ticket.setTitle("Ticket for " + member.getEffectiveName());
                ticket.setColor(RandomColorUtil.getRandomColor());
                ticket.setDescription(message.toString());
                finalTicket.append("The total is: ").append(new DecimalFormat("#.##").format(total)).append(Constants.Currency).append("\n") ;
                finalTicket.append("--------------------------------------------------------------");
                finalTicket.append("\n");
                finalTicket.append("\n");
                if(ticketMap.containsKey(UID)){
                    ticketMap.replace(UID,finalTicket.toString());
                }else{
                    ticketMap.put(UID,finalTicket.toString());
                }

                    privateChannel.retrieveMessageById(privateChannel.getLatestMessageId()).queue(message1 -> {
                        if(message1.getAuthor().isBot()){
                            message1.delete().queue();
                        }
                            });
                            privateChannel.sendMessage(ticket.build()).queue(message1 -> {
                                message1.addReaction("\uD83D\uDED2").queue();
                                message1.addReaction("❌").queue();
                            });
            });
        });
    }
    public static void deleteCart(User user){
        cart.remove(user.getId());
    }
    public static double getTotal(User user){
        Map<Item,Integer> cart = getShoppingCart(user.getId());
        double total = 0;
        for(Map.Entry<Item ,Integer> entry : cart.entrySet()){
            total = total + (entry.getKey().getPrice() * entry.getValue());
        }
        return total;
    }
}
