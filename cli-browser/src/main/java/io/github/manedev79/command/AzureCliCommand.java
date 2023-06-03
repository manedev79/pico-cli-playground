package io.github.manedev79.command;

import io.github.manedev79.internal.NativeShellExecutor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.*;

@Command(name = "azure", description = "List resources", version = "0.0.1")
public class AzureCliCommand implements Callable<Integer> {
    @Parameters(index = "0", description = "Resource type to list")
    private String resource;

    @Option(names = {"-o", "--output"},
            defaultValue = "json",
            description = "table,json,csv,... Default: json")
    private String option;

    private final NativeShellExecutor nativeShellExecutor;

    public AzureCliCommand() {
        this.nativeShellExecutor = new NativeShellExecutor();
    }

    public AzureCliCommand(NativeShellExecutor nativeShellExecutor) {
        this.nativeShellExecutor = nativeShellExecutor;
    }

    @Override
    public Integer call() throws Exception {
        return nativeShellExecutor.executeShellCommand("az %s list -o %s".formatted(resource, option));
    }


}
