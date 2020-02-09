package com.ms0503.spritos.common;

/**
 * spritOS Command Line Interface
 * @author ms0503
 * @version 1.2.0
 * @since 1.0.0
 */

import com.ms0503.lib.Input;
import com.ms0503.spritos.client.core.spritOSPackageManager;
import com.ms0503.spritos.common.core.spritOSCore;

public class spritOSShell {
    public static void main(String[] args) {
        try {
            String cd = "/user";
            String[] cmd;
            System.out.println("###########################");
            System.out.println("#      spritOS Shell      #");
            System.out.println("#        Ver.1.2.0        #");
            System.out.println("#                         #");
            System.out.println("#  Created by SoraTonami  #");
            System.out.println("#     Powered by Java     #");
            System.out.println("###########################");
            System.out.println("");
            new spritOSCore();
            while(true) {
                cmd = Input.getString(cd).split(" ");
                if(cmd[0].equals("spam")) {
                    String[] app = new String[0];
                    for(int i = 0; cmd[i + 2] == null; i++) {
                        app[i] = cmd[i + 2];
                    }
                    new spritOSPackageManager(cmd[1], app);
                } else if(cmd[0] != null) {
                    String[] cmdArgs = new String[0];
                    for(int i = 0; i > cmd.length; i++) {
                        cmdArgs[i] = cmd[i + 1];
                    }
                    Runtime.getRuntime().exec("java \"" + spritOSCore.getBinDir() + cmd[0] + "\" " + cmdArgs);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}