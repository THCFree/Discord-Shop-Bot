package dev.thcfree.ShopBot;

import dev.thcfree.ShopBot.Utils.SqliteUtil;

import java.util.ArrayList;

public class ItemManager {
    private static final ArrayList<Item> itemList = new ArrayList<>();

    public static void addItem(Item item){
        if(!itemList.contains(item)){
           itemList.add(item);
            SqliteUtil.addItem(item);
        }
    }
    public static void delItem(Item item){
        itemList.remove(item);
        SqliteUtil.delItem(item.getMsgId());
    }

    public static Item getItemById(String id){
        Item ret = null;
        for(Item i : itemList){
            if(i.getMsgId().equalsIgnoreCase(id)){
                ret = i;
            }
        }
        return ret;
    }
    public static void loadItem(Item item){
        if(!itemList.contains(item)){
            itemList.add(item);
        }
    }
    public static ArrayList<Item> getItemList(){
        return itemList;
    }
}
