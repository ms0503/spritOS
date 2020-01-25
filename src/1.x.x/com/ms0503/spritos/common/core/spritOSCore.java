package com.ms0503.spritos.common.core;

/**@author ms0503*/

public class spritOSCore extends Thread {
    public static final String id = "spritos_main";
    public static final String name = "spritOS";
    public static final String version = "1.2.0";
    public static final String latest = version;
    @Override
    public void run() {
        try {
            System.out.println("Welcome to spritOS " + version+ " !");
            System.out.print("Config Loading...");
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
