package code.shubham.demo.pagination.cursor.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CursorUtil {

    // Encode a cursor into a Base64 string
    public static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    // Decode a Base64 string into a cursor
    public static String decode(String cursor) {
        byte[] decodedBytes = Base64.getDecoder().decode(cursor);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
