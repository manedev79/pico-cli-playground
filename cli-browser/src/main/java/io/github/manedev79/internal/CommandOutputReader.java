package io.github.manedev79.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CommandOutputReader implements Runnable {
    private final InputStream inputStream;
    private final Consumer<String> consumer;

    public CommandOutputReader(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException("Reading from command output stream failed");
            }
            consumer.accept(line);
        }
    }
}
