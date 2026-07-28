package com.sky0427_coduck.managers;

public final class GameManager {

    private static boolean running = false;

    public static void start() {
        running = true;
    }

    public static void stop() {
        running = false;
    }

    public static boolean isRunning() {
        return running;
    }
}