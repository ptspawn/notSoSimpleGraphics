package org.herebdragons.utils;

public class Logger {

    private static boolean isLogging = false;
    private static boolean isDebug = false;

    public static void log(String msg) {
        if (isLogging)
            System.out.println(Thread.currentThread().getName() + " - " + msg + "\n");
    }

    public static void err(String msg) {
       // if (isLogging)
            System.err.println(Thread.currentThread().getName() + " - " + msg + "\n");
    }

    public static void debug(String msg){
        if (isDebug)
            System.out.println(Thread.currentThread().getName() + " - " + msg + "\n");
    }

    public static boolean isIsLogging() {
        return isLogging;
    }

    public static void setLogging(boolean isLogging) {
        Logger.isLogging = isLogging;
    }

    public static boolean isDebugging() {
        return isDebug;
    }

    public static void setDebugging(boolean isDebug) {
        Logger.isDebug = isDebug;
    }
}
