package com.ms0503.spritos.common.core;

/**
 * spritOS Config Loader
 * @author ms0503
 * @version 1.3.0
 * @since 1.0.0
 */

import java.io.*;
import java.util.Properties;

public class spritOSConfigCore extends Thread {
    private final String appName;
    private String[] cfg = new String[0];
    private final String mode;
    public spritOSConfigCore() {
        this("spritOS");
    }
    /**
     * Config Loading
     * @param appName ConfigLoadApp
     */
    public spritOSConfigCore(String appName) {
        this.mode = "load";
        this.appName = appName;
        this.start();
    }
    /**
     * Config Creating
     * @param appName ConfigCreateApp
     * @param cfg ConfigContents
     */
    public spritOSConfigCore(String appName, String[] cfg) {
        this.mode = "new";
        this.appName = appName;
        this.cfg = cfg;
        this.start();
    }
    @Override
    public void run() {
        if(mode == "new") {
            new NewCfg(appName, cfg);
        } else if(mode == "load") {
            new LoadCfg(appName);
        } else {
            return;
        }
    }
}
class LoadCfg extends Thread {
    String appName;
    public LoadCfg(String appName) {
        this.appName = appName;
        this.start();
    }
    @Override
    public void run() {
        try {
            File cfgFile = new File(spritOSCore.getHideDir() + File.separator + appName + ".cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
        } catch(IOException e) {
            new spritOSLogCore("spritos_cfg", "Config loading error.");
        }
    }
}
class NewCfg extends Thread {
    String appName;
    String[] cfg;
    public NewCfg(String appName, String[] cfg) {
        this.appName = appName;
        this.cfg = cfg;
        this.start();
    }
    @Override
    public void run() {
        try {
            FileWriter cfgFile = new FileWriter(spritOSCore.getHideDir() + File.separator + appName + ".cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            for (int i = 0; i > cfg.length; i++) {
                cfgWriter.println(cfg[i]);
            }
            cfgWriter.close();
        } catch (IOException e) {
            new spritOSLogCore("spritos_cfg", "Config make error.");
        }
    }
}