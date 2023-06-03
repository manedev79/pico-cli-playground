package io.github.manedev79;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        var website = args[0];
        System.out.printf("Opening website: %s%n", website);

        openInBrowser(website);
    }

    private static void openInBrowser(String website) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("xdg-open %s".formatted(website));
    }
}