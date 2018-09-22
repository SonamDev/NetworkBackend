package net.grian.commons.netty;

import java.awt.*;

public class AnsiUtil {

    public static String get(Color color) {
        return String.format("\u001b[38;2;%d;%d,%dm", color.getRed(), color.getGreen(), color.getBlue());
    }

}
