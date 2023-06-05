package org.mmi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CronParserTest {

    private CronParser cronParser = new CronParser();

    @Test
    void parseExampleFromIBMPage() {
        String input = "32 18 17,21,29 11 Mon,wEd 2021 command";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         32\n" +
                "hour           18\n" +
                "day of month   17 21 29\n" +
                "month          11\n" +
                "day of week    1 3\n" +
                "year           2021\n" +
                "command        command\n";
        assertEquals(w(expected), w(actual));
    }

    @Test
    void parseFirstOfTheMonth() {
        String input = "15 14 1 * * 2020/3 command2";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         15\n" +
                "hour           14\n" +
                "day of month   1\n" +
                "month          1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week    1 2 3 4 5 6 7\n" +
                "year           2020 2023\n" +
                "command        command2";
        assertEquals(w(expected), w(actual));
    }

    @Test
    void parseNoonAndMidnightEverySecondMonth() {
        String input = "0 0,12 1 */2 * 2022,2023-2024 command4";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         0\n" +
                "hour           0 12\n" +
                "day of month   1\n" +
                "month          1 3 5 7 9 11\n" +
                "day of week    1 2 3 4 5 6 7\n" +
                "year           2022 2023 2024\n" +
                "command        command4\n";
        assertEquals(w(expected), w(actual));
    }

    @Test
    void parseAWeirdSchedule() {
        String input = "23,27,21 0-10/2,13-24/3 */4 * tue-thu * command5";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         21 23 27\n" +
                "hour           0 2 4 6 8 10 13 16 19 22\n" +
                "day of month   1 5 9 13 17 21 25 29\n" +
                "month          1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week    2 3 4\n" +
                "year           2020 2021 2022 2023 2024 2025\n" +
                "command        command5\n";
        assertEquals(w(expected), w(actual));
    }

    @Test
    void withYear() {
        String input = "32 18 17,21,29 11 Mon,wEd 2022-2023 command";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         32\n" +
                "hour           18\n" +
                "day of month   17 21 29\n" +
                "month          11\n" +
                "day of week    1 3\n" +
                "year           2022 2023\n" +
                "command        command\n";
        assertEquals(w(expected), w(actual));
    }


    @Test
    void withComment() {
        String input = "32 18 17,21,29 11 Mon,wEd 2022-2023 command sassassa asas # the comment";
        String actual = cronParser.parse(input);

        String expected = "" +
                "minute         32\n" +
                "hour           18\n" +
                "day of month   17 21 29\n" +
                "month          11\n" +
                "day of week    1 3\n" +
                "year           2022 2023\n" +
                "command        command\n" +
                "comment        the comment\n";
        assertEquals(w(expected), w(actual));
    }


    private String w(String s) {
        return s.replaceAll("\\s", "");
    }
}


