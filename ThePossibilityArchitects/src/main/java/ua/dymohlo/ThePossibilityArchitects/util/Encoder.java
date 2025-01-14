package ua.dymohlo.ThePossibilityArchitects.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Encoder {
    public static String encodeText(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decodeText(String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);
    }
}