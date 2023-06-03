package io.github.manedev79.command;

import io.github.manedev79.internal.NativeShellExecutor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Command(name = "browse", description = "Opens the given website", version = "0.0.1")
public class GithubCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "Website to open")
    private String website;

    private NativeShellExecutor nativeShellExecutor;

    public GithubCommand() {
        this.nativeShellExecutor = new NativeShellExecutor();
    }
    public GithubCommand(NativeShellExecutor nativeShellExecutor) {
        this.nativeShellExecutor = nativeShellExecutor;
    }

    @Override
    public Integer call() throws Exception {
        System.out.printf("Opening website: %s%n", website);
        openInBrowser(website);
        return 0;
    }

    private void openInBrowser(String website) throws IOException, ExecutionException, InterruptedException {
        nativeShellExecutor.executeShellCommand("xdg-open %s".formatted(website));
    }
}
