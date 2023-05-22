package org.mmi;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class PartExpander {
    public String expandPart(String part, int min, int max) {
        Set<Integer> values = new HashSet<>();
        String[] subParts = part.split(",");

        for (String sub : subParts) {
            values.addAll(expandSubPart(sub, min, max));
        }

        return values.stream().sorted().map(Object::toString).collect(Collectors.joining(" "));
    }

    private Collection<Integer> expandSubPart(String subPart, int min, int max) {
        try {
            return executeExpandSubPart(subPart, min, max);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.err.println("Invalid cron string near: {" + subPart + "}." + " Remember to remove unnecessary white spaces.");
            throw new IllegalStateException(e);
        }
    }

    private Collection<Integer> executeExpandSubPart(String subPart, int min, int max) {
        int step = 1;
        int begin = min;
        int end = max;
        String rangeString = subPart;

        if (subPart.contains("/")) {
            String[] split = subPart.split("/");
            rangeString = split[0];
            step = parseInt(split[1]);
        }

        if (!rangeString.equals("*")) {
            if (rangeString.contains("-")) {
                String[] rangeParts = rangeString.split("-");
                begin = max(min, parseInt(rangeParts[0]));
                end = min(max, parseInt(rangeParts[1]));
            } else {
                begin = max(min, parseInt(rangeString));
                end = min(max, parseInt(rangeString));
            }
        }

        return generateNumbers(begin, end, step);
    }

    private Collection<Integer> generateNumbers(int begin, int end, int step) {
        List<Integer> values = new ArrayList<>();
        for (int i = begin; i <= end; i += step) {
            values.add(i);
        }
        return values;
    }
}
