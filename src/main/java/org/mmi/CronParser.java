package org.mmi;

import java.util.Optional;

import static java.lang.String.format;

public class CronParser {

    private final static String[] DAYS = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
    private final static String[] MONTHS = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    private final PartExpander partExpander = new PartExpander();

    public String parse(String cronString) {
        String parts[] = cronString.split(" ");
        if (parts.length < 7) {
            throw new IllegalArgumentException("Incorrect cron string. See reference -> https://crontab.guru/");
        }

        StringBuilder result = new StringBuilder();
        result.append(format("%-14s %s%n", "minute", partExpander.expandPart(parts[0], 0, 59)));
        result.append(format("%-14s %s%n", "hour", partExpander.expandPart(parts[1], 0, 23)));
        result.append(format("%-14s %s%n", "day of month", partExpander.expandPart(parts[2], 1, 31)));
        result.append(format("%-14s %s%n", "month", partExpander.expandPart(replaceAlphanumeric(parts[3], MONTHS), 1, 12)));
        result.append(format("%-14s %s%n", "day of week", partExpander.expandPart(replaceAlphanumeric(parts[4], DAYS), 1, 7)));
        result.append(format("%-14s %s%n", "year", partExpander.expandPart(parts[5], 2020, 2025)));
        result.append(format("%-14s %s%n", "command", parts[6]));
        extractComment(cronString).ifPresent(com -> result.append(format("%-14s %s%n", "comment", com)));
        result.append("\n");
        return result.toString();
    }

    private Optional<String> extractComment(String cronString) {
        if (cronString.contains("#")) {
            return Optional.of(cronString.substring(cronString.indexOf('#') + 1).trim());
        }
        return Optional.empty();
    }


    private String replaceAlphanumeric(String input, String[] values) {
        input = input.toLowerCase();
        for (int i = 0; i < values.length; i++) {
            input = input.replaceAll("(?i)\\b" + values[i] + "\\b", Integer.valueOf(i + 1).toString());
        }
        return input;
    }
}
