package io.github.manedev79;

import io.github.manedev79.command.GithubCommand;
import picocli.CommandLine;

public class App {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new GithubCommand()).execute(args);
        System.exit(exitCode);
    }


}