package org.mmi;

public class Main {

    private final static String README = "https://github.com/mmisiol/cron-command-expander/blob/master/readme.md";

    public static void main(String[] args) {
        System.out.println(args[0]);

        if (args.length < 1) {
            System.out.println("Missing input. Read instructions here: " + README);
        } else {
            System.out.println(new CronParser().parse(args[0]));
        }
    }
}