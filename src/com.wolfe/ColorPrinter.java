package com.wolfe;

/*
 * Created by Jeremy on 10/20/2016.
 */


// copied from https://github.com/minneapolis-edu/color-printer
enum TextCol {
    RED("\u001B[31m"),
    GREEN ( "\u001B[32m"),
    YELLOW ( "\u001B[33m"),
    BLUE ( "\u001B[34m"),
    MAGENTA( "\u001B[35m"),
    CYAN ( "\u001B[36m"),
    WHITE ( "\u001B[37m"),
    BLACK ( "\u001B[30m");

    private final String value;
    TextCol(String val) {this.value = val;}
    public String value() {return value;}
}


enum TextBk {
    RED("\u001B[41m"),
    GREEN ( "\u001B[42m"),
    YELLOW ( "\u001B[43m"),
    BLUE ( "\u001B[44m"),
    MAGENTA( "\u001B[45m"),
    CYAN ( "\u001B[46m"),
    WHITE ( "\u001B[47m"),
    BLACK ( "\u001B[40m");

    final String value;
    TextBk(String val) {this.value = val;}
    public String value() {return value;}

}

class ColorPrinter {

    private static final String ANSI_reset_color = "\u001B[0m";

    public static String print(String text, TextCol color)  {
        return color.value() + text + ANSI_reset_color;
    }

    public static void print(String text, TextCol color, TextBk background) {
        System.out.println(color.value() + background.value() + text + ANSI_reset_color);
    }

}
