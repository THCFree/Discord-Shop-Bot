package dev.thcfree.ShopBot.Utils;

import java.awt.*;
import java.util.Random;

public class RandomColorUtil {
    public static Color getRandomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
