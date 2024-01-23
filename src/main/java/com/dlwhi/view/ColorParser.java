package com.dlwhi.view;

import com.diogonunes.jcolor.Attribute;

import com.dlwhi.exceptions.MissingColorException;

public class ColorParser {

    public static Attribute mapText(String value) {
        switch (value) {
            case "black": return Attribute.BLACK_TEXT();
            case "red": return Attribute.RED_TEXT();
            case "green": return Attribute.GREEN_TEXT();
            case "yellow": return Attribute.YELLOW_TEXT();
            case "blue": return Attribute.BLUE_TEXT();
            case "magenta": return Attribute.MAGENTA_TEXT();
            case "cyan": return Attribute.CYAN_TEXT();
            case "white": return Attribute.WHITE_TEXT();
            case "bright_black": return Attribute.BRIGHT_BLACK_TEXT();
            case "bright_red": return Attribute.BRIGHT_RED_TEXT();
            case "bright_green": return Attribute.BRIGHT_GREEN_TEXT();
            case "bright_yellow": return Attribute.BRIGHT_YELLOW_TEXT();
            case "bright_blue": return Attribute.BRIGHT_BLUE_TEXT();
            case "bright_magenta": return Attribute.BRIGHT_MAGENTA_TEXT();
            case "bright_cyan": return Attribute.BRIGHT_CYAN_TEXT();
            case "bright_white": return Attribute.BRIGHT_WHITE_TEXT();
            default: throw new MissingColorException();
        }
    }
    
    public static Attribute mapBack(String value) {
        switch (value) {
            case "black": return Attribute.BLACK_BACK();
            case "red": return Attribute.RED_BACK();
            case "green": return Attribute.GREEN_BACK();
            case "yellow": return Attribute.YELLOW_BACK();
            case "blue": return Attribute.BLUE_BACK();
            case "magenta": return Attribute.MAGENTA_BACK();
            case "cyan": return Attribute.CYAN_BACK();
            case "white": return Attribute.WHITE_BACK();
            case "bright_black": return Attribute.BRIGHT_BLACK_BACK();
            case "bright_red": return Attribute.BRIGHT_RED_BACK();
            case "bright_green": return Attribute.BRIGHT_GREEN_BACK();
            case "bright_yellow": return Attribute.BRIGHT_YELLOW_BACK();
            case "bright_blue": return Attribute.BRIGHT_BLUE_BACK();
            case "bright_magenta": return Attribute.BRIGHT_MAGENTA_BACK();
            case "bright_cyan": return Attribute.BRIGHT_CYAN_BACK();
            case "bright_white": return Attribute.BRIGHT_WHITE_BACK();
            default: throw new MissingColorException();
        }
    }
}
