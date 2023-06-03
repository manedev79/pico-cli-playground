package io.github.manedev79.internal;

public class Host {
    public static boolean isWindows = System.getProperty("os.name")
                                            .toLowerCase()
                                            .startsWith("windows");
    public static boolean isLinux = System.getProperty("os.name")
                                          .toLowerCase()
                                          .startsWith("linux");
}
