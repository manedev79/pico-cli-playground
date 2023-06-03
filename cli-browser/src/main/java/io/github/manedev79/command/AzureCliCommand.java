package io.github.manedev79.command;

import io.github.manedev79.internal.CommandOutputReader;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

@Command(name = "azure", description = "List resources", version = "0.0.1")
public class AzureCliCommand implements Callable<Integer> {
    @Parameters(index = "0", description = "Resource type to list")
    private String resource;

    @Option(names = {"-o", "--output"},
            defaultValue = "json",
            description = "table,json,csv,... Default: json")
    private String option;

    private final ExecutorService executorService;

    public AzureCliCommand() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public AzureCliCommand(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Integer call() throws Exception {
        return invokeCommand();
    }

    private int invokeCommand() throws IOException, ExecutionException, InterruptedException {
        String command = "az %s list -o %s".formatted(resource, option);
        System.out.println(command);
        return executeNative(command);
    }

    private int executeNative(String command) throws IOException, InterruptedException, ExecutionException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", command);
        builder.directory(new File(System.getProperty("user.home")));
        builder.redirectErrorStream(true);
        Process process = builder.start();
        CommandOutputReader commandOutputReader = new CommandOutputReader(process.getInputStream(), System.out::println);
        Future<?> future = executorService.submit(commandOutputReader);
        future.get();
        return process.waitFor();
    }

}
