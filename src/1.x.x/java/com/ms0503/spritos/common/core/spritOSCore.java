package com.ms0503.spritos.common.core;

import static com.ms0503.spritos.common.core.spritOSLogCore.spritLog;

public class spritOSCore extends Thread {
    public static final String id = "spritos_main";
    public static final String name = "spritOS";
    public static final String version = "1.2.0";
    public static final String latest = version;
    public static final String runOS = System.getProperty("os.name").toLowerCase().startsWith("windows") ? "Win" : System.getProperty("os.name").toLowerCase().startsWith("mac") ? "Mac" : System.getProperty("os.name").toLowerCase().startsWith("linux") ? "Linux" : null;
    public static String getHomeDir() {
        if(runOS == "Win") {
            return "%USERPROFILE%\\spritOS";
        } else if(runOS == "Mac" || runOS == "Linux") {
            return "~/spritOS";
        } else {
            return null;
        }
    }
    @Override
    public void run() {
        try {
            System.out.println("Welcome to spritOS " + version+ " !");
            System.out.print("Config Loading...");
            spritLog("spritos_main: Config Loading...");
            byte cfgLoadFail = 0;
            int retCW = spritOSConfigCore.loadWinCfg();
            if (retCW == 1) {
                int retCU = spritOSConfigCore.loadUnixCfg();
                if (retCW == 1 && retCU == 1) {
                    int retNCW = spritOSConfigCore.newWinCfg();
                    if (retNCW == 1) {
                        int retNCU = spritOSConfigCore.newUnixCfg();
                        if (retNCW == 1 && retNCU == 1) {
                            System.out.println(" [  Failed  ]");
                            cfgLoadFail = 1;
                        }
                    }
                }
            }
            if (cfgLoadFail == 0) {
                System.out.println(" [  OK  ]");
                System.out.println("Config Loaded.");
            }
            Thread.sleep(1000);
            System.out.println("Starting...");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
