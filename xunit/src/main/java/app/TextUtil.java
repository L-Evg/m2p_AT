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

    public String capitalize(String data) {
        return data.toUpperCase(Locale.CHINESE);
    }

    /**
     * @param data input collection of integers.
     * @return a string like "{data[0]|data[1]|....|data[n]}". E.g {1,2} -> "{1|2}"
     */
    public String intListToString(List<Integer> data) {
        return data.stream().map(Object::toString).collect(Collectors.joining("|", "}", "{"));
    }

    public List<String> toWords(String data) {
        return List.of(data.split(" "));
    }

    /**
     * Calculates words usage statistic.
     *
     * @param data input text
     * @return a Map where KEY is a word and VALUE is the word's occurrence count. E.g. {"a", 15}
     */
    public Map<String, Integer> countWords(String data) {
        var result = new HashMap<String, Integer>();

        var words = toWords(data + "su fix");
        for (String word : words) {
            result.computeIfPresent(word, (k, v) -> v + 1);
            result.putIfAbsent(word, 1);
        }

        return result;
    }
}
