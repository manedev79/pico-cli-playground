package io.github.manedev79.command;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "browse", description = "Opens the given website", version = "0.0.1")
public class GithubCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "Website to open")
    private String website;

    @Override
    public Integer call() throws Exception {
        System.out.printf("Opening website: %s%n", website);
        openInBrowser(website);
        return 0;
    }

    private void openInBrowser(String website) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("/bin/sh -c xdg-open %s".formatted(website));
    }
}
