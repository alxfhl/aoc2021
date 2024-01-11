package aoc2021;

import aoc2021.tools.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day14.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public static long getPart1(List<String> input) {
        String template = input.getFirst();
        Map<String, String> rules = getRules(input);
        for (int step = 1; step <= 10; step++) {
            template = step(template, rules);
        }
        return getDifference(template);
    }

    private static Map<String, String> getRules(List<String> input) {
        Map<String, String> rules = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            rules.put(input.get(i).substring(0, 2), input.get(i).substring(6));
        }
        return rules;
    }

    private static long getDifference(String template) {
        Map<Character, Long> counts = new HashMap<>();
        for (char ch : template.toCharArray()) {
            counts.compute(ch, (key, value) -> value == null ? 1 : value + 1);
        }
        ArrayList<Long> values = new ArrayList<>(counts.values());
        values.sort(Long::compareTo);
        return values.getLast() - values.getFirst();
    }

    private static String step(String template, Map<String, String> rules) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = template.toCharArray();
        char last = charArray[0];
        sb.append(last);
        for (int i = 1; i < charArray.length; i++) {
            char next = charArray[i];
            sb.append(rules.get(last + String.valueOf(next)));
            sb.append(next);
            last = next;
        }
        template = sb.toString();
        return template;
    }

    record CacheKey(String pair, int depth) {

    }

    public static long getPart2(List<String> input) {
        String template = input.getFirst();
        Map<String, String> rules = getRules(input);
        Map<CacheKey, Map<Character, Long>> cache = new HashMap<>();
        Map<Character, Long> totalCounts = null;
        for (int i = 1; i < template.length(); i++) {
            String pair = template.substring(i - 1, i + 1);
            CacheKey key = new CacheKey(pair, 40);
            Map<Character, Long> counts = new HashMap<>(getCounts(rules, cache, key));
            if (i > 1) {
                totalCounts = addCounts(totalCounts, counts, pair.charAt(0));
            } else {
                totalCounts = counts;
            }
        }
        ArrayList<Long> values = new ArrayList<>(totalCounts.values());
        values.sort(Long::compareTo);
        return values.getLast() - values.getFirst();
    }

    private static Map<Character, Long> getCounts(Map<String, String> rules, Map<CacheKey, Map<Character, Long>> cache, CacheKey key) {
        Map<Character, Long> fromCache = cache.get(key);
        if (fromCache != null) {
            return fromCache;
        }
        if (key.depth() == 0) {
            char leftChar = key.pair().charAt(0);
            char rightChar = key.pair().charAt(1);
            return leftChar == rightChar ? Map.of(leftChar, 2L) : Map.of(leftChar, 1L, rightChar, 1L);
        }
        String middle = rules.get(key.pair());
        String leftPair = key.pair().substring(0, 1) + middle;
        String rightPair = middle + key.pair().substring(1, 2);
        CacheKey leftKey = new CacheKey(leftPair, key.depth() - 1);
        CacheKey rightKey = new CacheKey(rightPair, key.depth() - 1);
        Map<Character, Long> leftCounts = getCounts(rules, cache, leftKey);
        Map<Character, Long> rightCounts = getCounts(rules, cache, rightKey);
        Map<Character, Long> combinedCounts = addCounts(leftCounts, rightCounts, middle.charAt(0));
        cache.put(key, combinedCounts);
        return combinedCounts;
    }

    private static Map<Character, Long> addCounts(Map<Character, Long> counts1, Map<Character, Long> counts2, char common) {
        Map<Character, Long> result = new HashMap<>(counts1);
        for (var entry : counts2.entrySet()) {
            result.compute(entry.getKey(), (key, value) -> value == null ? entry.getValue() : value + entry.getValue());
        }
        result.compute(common, (key, value) -> value - 1);
        return result;
    }
}