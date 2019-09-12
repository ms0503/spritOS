package com.ms0503.spritos.common.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**@author ms0503*/

public class spritOSConfigCore {
    /**
     * newWinCfg()
     * Windows環境のコンフィグ作成メソッド。
     * try-catch(-finally)とnewUnixCfg()と一緒に使うと
     * 良い感じ。かも?
     *
     * 戻り値は 成功=0 失敗=1
     * @return 0 | 1
     */
    public int newWinCfg() {
        try {
            FileWriter cfgFile = new FileWriter("%USERPROFILE%/.spritOS/config.cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            cfgWriter.println("// Language Setting");
            cfgWriter.println("lang=ja");
            cfgWriter.println("");
            cfgWriter.println("// Home Directory Setting");
            cfgWriter.println("home=%USERPROFILE%/spritOS");
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }

    /**
     * loadWinCfg()
     * Windows環境のコンフィグ読み込みメソッド。
     * try-catch(-finally)とloadUnixCfg()と使うと
     * 良さげかな?
     *
     * 戻り値は 成功=0 失敗=1
     * @return 0 | 1
     */
    public int loadWinCfg() {
        try {
            File cfgFile = new File("%USERPROFILE%/.spritOS/config.cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }

    /**
     * newUnixCfg()
     * UnixやLinuxのコンフィグ作成メソッド。
     * try-catch(-finally)とnewWin(ry
     *
     * 戻り値は 成(ry
     * @return 0 | 1
     */
    public int newUnixCfg() {
        try {
            FileWriter cfgFile = new FileWriter("~/.spritOS/config.cfg");
            PrintWriter cfgWriter = new PrintWriter(new BufferedWriter(cfgFile));
            cfgWriter.println("// Language Setting");
            cfgWriter.println("lang=ja");
            cfgWriter.println("");
            cfgWriter.println("// Home Directory Setting");
            cfgWriter.println("home=~/spritOS");
            cfgWriter.close();
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }

    /**
     * loadUnixCfg()
     * UnixやLinuxのコンフィグ読み込みメソッド。
     * t(ry
     *
     * 戻(ry
     * @return 0 | 1
     */
    public int loadUnixCfg() {
        try {
            File cfgFile = new File("~/.spritOS/config.cfg");
            Properties cfg = new Properties();
            cfg.load(new FileReader(cfgFile));
            return 0;
        } catch(IOException e) {
            return 1;
        }
    }
}