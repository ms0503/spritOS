package com.ms0503.spritos.common;

import com.ms0503.lib.Input;
import com.ms0503.spritos.client.core.spritOSPackageManager;
import com.ms0503.spritos.common.core.spritOSCore;

public class spritOSShell {
    public static void main(String[] args) {
        try {
            String cd = "/user";
            String[] cmd = new String[0];
            spritOSCore core = new spritOSCore();
            System.out.println("#################");
            System.out.println("# spritOS Shell #");
            System.out.println("#   Ver.1.1.0   #");
            System.out.println("#################");
            System.out.println("");
            core.start();
            while (true) {
                for (int i = 0; cmd != null; i++) {
                    cmd[i] = Input.getString(cd);
                }
                if (cmd[0] == "spam") {
                    spritOSPackageManager spam;
                    String[] app = new String[0];
                    if (cmd[1] == "upload") {
                        for (int i = 0; cmd[i + 3] == null; i++) {
                            app[i] = cmd[i + 3];
                        }
                        spam = new spritOSPackageManager("upload", cmd[2], app);
                        spam.start();
                    } else {
                        for (int i = 0; cmd[i + 2] == null; i++) {
                            app[i] = cmd[i + 2];
                        }
                        spam = new spritOSPackageManager(cmd[1], app);
                        spam.start();
                    }
                } else if (cmd[0] != null) {
                    if(spritOSCore.runOS == "Win") {
                        Runtime.getRuntime().exec("java -jar \"%USERPROFILE%\\spritOS\\bin\\" + cmd + ".spr\"");
                    } else if(spritOSCore.runOS == "Mac" || spritOSCore.runOS == "Linux") {
                        Runtime.getRuntime().exec("java -jar \"~/spritOS/bin/" + cmd + ".spr");
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}