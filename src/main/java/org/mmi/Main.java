package org.mmi;

public class Main {

    private final static String README = "readme";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Read instructions here: " + README);
        } else {
            System.out.println(new CronParser().parse(args[0]));
        }
    }
}