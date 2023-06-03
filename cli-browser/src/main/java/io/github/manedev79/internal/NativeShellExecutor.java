package io.github.manedev79.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NativeShellExecutor {

    private final ExecutorService executorService;

    public NativeShellExecutor() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public NativeShellExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public int executeShellCommand(String command) throws IOException, InterruptedException, ExecutionException {
        ProcessBuilder builder = new ProcessBuilder();
        List<String> commands = new ArrayList<>(nativeShell());
        commands.add(command);
        builder.command(commands);
        builder.directory(new File(System.getProperty("user.home")));
        builder.redirectErrorStream(true);

        System.out.printf("Command to be executed: %s\n", builder.command());

        Process process = builder.start();
        CommandOutputReader commandOutputReader = new CommandOutputReader(process.getInputStream(), System.out::println);
        Future<?> future = executorService.submit(commandOutputReader);
        future.get();
        return process.waitFor();
    }

    protected List<String> nativeShell() {
        if (isWindows()) {
            return List.of("cmd.exe", "/c");
        } else {
            return List.of("sh", "-c");
        }
    }

    protected boolean isWindows() {
        return System.getProperty("os.name")
                     .toLowerCase()
                     .startsWith("windows");
    }
}
