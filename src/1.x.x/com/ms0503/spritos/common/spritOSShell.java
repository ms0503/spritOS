package com.ms0503.spritos.common;

import com.ms0503.lib.Input;
import com.ms0503.spritos.common.core.spritOSCore;
import com.ms0503.spritos.server.core.spritOSPackageManager;

import java.io.IOException;

public class spritOSShell {
    public static void main(String[] args) {
        String cd = "/user";
        String[] cmd = new String[0];
        spritOSCore core = new spritOSCore();
        System.out.println("#################");
        System.out.println("# spritOS Shell #");
        System.out.println("#   Ver.1.1.0   #");
        System.out.println("#################");
        System.out.println("");
        try {
            core.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
        while(true) {
            for(int i = 0; cmd != null; i++) {
                cmd[i] = Input.getString(cd);
            }
            if(cmd[0] == "spam") {
                if(cmd[1] == "install") {
                    try {
                        spritOSPackageManager.spamInstall(cmd);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else if(cmd[1] == "uninstall") {
                    try {
                        spritOSPackageManager.spamUninstall(cmd);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else if(cmd[1] == "upload") {
                    try {
                        spritOSPackageManager.spamUpload(cmd[2], cmd[3]);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ;
                }
            } else if(cmd[0] != null) {
                try {
                    Runtime rt = Runtime.getRuntime();
                    rt.exec("java -jar \"%USERPROFILE%\\spritOS\\bin\\" + cmd + ".spr\"");
                } catch(IOException e) {
                    try {
                        e.printStackTrace();
                        Runtime rt = Runtime.getRuntime();
                        rt.exec("java -jar \"~/spritOS/bin/" + cmd + ".spr\"");
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}