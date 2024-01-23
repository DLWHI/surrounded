package com.dlwhi.config;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class FileLoader {
    public static String[] loadAsLines(String filePath, String ignore) {
        LinkedList<String> lines = new LinkedList<>();
        try (Scanner reader = new Scanner(
                Objects.requireNonNull(FileLoader.class.getResourceAsStream(filePath))
        )) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.chars().allMatch(Character::isWhitespace) && !line.startsWith(ignore)) {
                    lines.add(line);
                }
            }
        }
        return lines.toArray(new String[0]);
    }
}
