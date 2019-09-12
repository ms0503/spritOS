package com.ms0503.spritos.common;

import com.ms0503.lib.*;
import com.ms0503.spritos.common.*;
import com.ms0503.spritos.common.annotation.*;
import com.ms0503.spritos.common.core.*;

/**@author ms0503*/

@Program(id="spritos_main", name="spritOS", version="1.0.0", author="ms0503")
public class spritOS {
    public static void main(String[] args) {
        public static String osVersion = "1.0.0";
        System.out.println("Welcome to spritOS " + osVersion + " !");
        System.out.print("Config Loading...");
        byte cfgLoadFail = 0;
        int retCW = loadWinCfg();
        if(retCW == 1) {
            int retCU = loadUnixCfg();
            if(retCW == 1 && retCU == 1) {
                int retNCW = newWinCfg();
                if(retNCW == 1) {
                    int retNCU = newUnixCfg();
                    if(retNCW == 1 && retNCU == 1) {
                        System.out.println(" [  Failed  ]");
                        cfgLoadFail = 1;
                    }
                }
            }
        }
        if(cfgLoadFail == 0) {
            System.out.println(" [  OK  ]");
            System.out.println("Config Loaded.");
        }
        Thred.sleep(1000);
        System.out.println("Starting...");
        Thred.sleep(2000);

    }
}
