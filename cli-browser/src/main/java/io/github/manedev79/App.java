package io.github.manedev79;

import io.github.manedev79.command.AzureCliCommand;
import io.github.manedev79.command.GithubCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "cli-browser",
        description = "Browse the web and tools",
        version = "0.0.1.beta0",
        subcommands = {
                AzureCliCommand.class,
                GithubCommand.class
        })
public class App {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


}