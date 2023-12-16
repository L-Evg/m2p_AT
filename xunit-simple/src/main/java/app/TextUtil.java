package app;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contains a few text related utility methods.
 */
public class TextUtil {

    public String toLowerCase(String data) {
        return data.toLowerCase();
    }

    /**
     * @param data input integer
     * @return a string representation of the input integer. E.g 1 -> "1"
     */
    public String intToString(Integer data) {
        return String.valueOf(data);
    }
}
