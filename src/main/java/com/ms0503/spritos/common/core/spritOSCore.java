package com.ms0503.spritos.common.core;

/**
 * spritOS Core
 * @author ms0503
 * @version 1.3.0
 * @since 1.0.0
 */

public class spritOSCore extends Thread {
    public static final String id = "spritos_main";
    public static final boolean isLinux = System.getProperty("os.name").toLowerCase().startsWith("linux");
    public static final boolean isMac = System.getProperty("os.name").toLowerCase().startsWith("mac");
    public static final boolean isWin = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static final String version = "1.3.0";
    public static final String latest = version;
    public spritOSCore() {
        this.start();
    }

    /**
     * getBinDir method
     * @return BinariesDirectory
     */
    public static String getBinDir() {
        if(isWin) {
            return getHomeDir() + "\\bin\\";
        } else if(isMac || isLinux) {
            return getHomeDir() + "/bin/";
        } else {
            return null;
        }
    }
    /**
     * getHideDir method
     * @return HiddenDirectory
     */
    public static String getHideDir() {
        if(isWin) {
            return "%USERPROFILE%\\.spritOS\\";
        } else if(isMac || isLinux) {
            return "~/.spritOS/";
        } else {
            return null;
        }
    }
    /**
     * getHomeDir method
     * @return UserHomeDirectory
     */
    public static String getHomeDir() {
        if(isWin) {
            return "%USERPROFILE%\\spritOS\\";
        } else if(isMac || isLinux) {
            return "~/spritOS/";
        } else {
            return null;
        }
    }
    /**
     * getLogDir method
     * @return LogsDirectory
     */
    public static String getLogDir() {
        if(isWin) {
            return getHomeDir() + "\\log\\";
        } else if(isMac || isLinux) {
            return getHomeDir() + "/log/";
        } else {
            return null;
        }
    }
    @Override
    public void run() {
        try {
            System.out.println("Welcome to spritOS " + version+ " !");
            System.out.print("Config Loading...");
            new spritOSLogCore("spritos_main", "Config Loading...");
            Thread.sleep(1000);
            new spritOSConfigCore("load");
            System.out.println(" [  OK  ]");
            System.out.println("Starting...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
