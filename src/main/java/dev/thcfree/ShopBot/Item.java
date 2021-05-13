package dev.thcfree.ShopBot;

public class Item {

    private String name;
    private Double price;
    private String msgId;
    private String channelId;
    public Item(String name, Double price , String msgId, String channelId){
        this.name = name;
        this.price = price;
        this.msgId = msgId;
        this.channelId = channelId;
    }

    public String getName(){
        return name;
    }
    public String getMsgId(){
        return msgId;
    }
    public String getChannelId(){
        return channelId;
    }
    public double getPrice(){
        return price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(Double price){
        this.price = price;
    }
}
