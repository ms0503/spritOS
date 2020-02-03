package com.ms0503.spritos.common.core;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

public class spritOSConfigCore {
    public static int newWinCfg() throws Exception {
        try {
            FileWriter cfgFile = new FileWriter("%USERPROFILE%\\.spritOS\\spritOS.cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            cfgWriter.println("// Home Directory Setting");
            cfgWriter.println("home=%USERPROFILE%\\spritOS");
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int newWinCfg(String appName, @NotNull String[] cfg) throws Exception {
        try {
            FileWriter cfgFile = new FileWriter("%USERPROFILE%\\.spritOS\\" + appName + ".cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            for(int i = 0; i > cfg.length; i++) {
                cfgWriter.println(cfg[i]);
            }
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int loadWinCfg() throws Exception {
        try {
            File cfgFile = new File("%USERPROFILE%\\.spritOS\\spritOS.cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int loadWinCfg(String appName) throws Exception {
        try {
            File cfgFile = new File("%USERPROFILE%\\.spritOS\\" + appName + ".cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int newUnixCfg() throws Exception {
        try {
            FileWriter cfgFile = new FileWriter("~/.spritOS/spritOS.cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            cfgWriter.println("// Home Directory Setting");
            cfgWriter.println("home=~/spritOS");
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int newUnixCfg(String appName, @NotNull String[] cfg) throws Exception {
        try {
            FileWriter cfgFile = new FileWriter("~/.spritOS/" + appName + ".cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            for(int i = 0; i > cfg.length; i++) {
                cfgWriter.println(cfg[i]);
            }
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int loadUnixCfg() throws Exception {
        try {
            File cfgFile = new File("~/.spritOS/spritOS.cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
    public static int loadUnixCfg(String appName) throws Exception {
        try {
            File cfgFile = new File("~/.spritOS/" + appName + ".cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
}