package com.m2p.at.ftbtests.data;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Contains miscellaneous utility methods like string/date related ones etc.
 */
@Slf4j
@Component
public class Utils {
    private final Faker faker;

    @Autowired
    public Utils(Faker faker) {
        this.faker = faker;
    }

    public static boolean toBeOrNotToBe() {
        return Math.random() > 0.5d;
    }

    public static <E> E getRandomItem(Random random, List<E> items) {
        checkSize(items);

        return items.get(random.nextInt(items.size()));
    }

    public static <E> E getRandomItem(List<E> items) {
        return getRandomItem(new Random(), items);
    }

    @SafeVarargs
    public static <E> E getRandomItem(E... items) {
        return getRandomItem(new Random(), List.of(items));
    }

    public static <E> List<E> getRandomItems(List<E> items, int maxCount) {
        int adjustedMaxCount = Math.min(maxCount, items.size());
        Objects.checkIndex(adjustedMaxCount, items.size() + 1);

        var result = new HashSet<E>();
        var rnd = new Random();
        for (int i = 0; i < maxCount; i++) {
            result.add(getRandomItem(rnd, items));
        }

        return new ArrayList<>(result);
    }

    public static <E> List<E> getSeveralRandomItems(List<E> items) {
        checkSize(items);
        var targetCount = new Random().nextInt(items.size() + 1);

        if (0 == targetCount) {
            targetCount++;
        }

        return getRandomItems(items, targetCount);
    }

    private static <E> void checkSize(List<E> items) {
        if (Objects.isNull(items) || items.isEmpty())
            throw new IllegalArgumentException("The list of items shall not be null or empty");
    }

    public String getRandomString(String prefix, int finalLength) {
        return prefix + faker.regexify("\\w{" + (finalLength - prefix.length()) + "}");
    }

    public String getRandomString(int finalLength) {
        return getRandomString("", finalLength);
    }

    public static String provideAlteredValue(String upd, String value) {
        var newValue = String.valueOf(value);
        if (newValue.startsWith(upd)) {
            newValue = newValue.replaceAll(upd, "");
        } else {
            newValue = upd + newValue;
        }
        return newValue;
    }
}
