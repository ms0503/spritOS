package com.ms0503.spritos.client.core;

import com.ms0503.spritos.common.core.spritOSCore;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.ms0503.spritos.common.core.spritOSLogCore.spritLog;

public class spritOSPackageManager extends Thread {
    String cmd;
    String[] appName;
    String path;
    public spritOSPackageManager(String cmd, String[] appName) {
        this.cmd = cmd;
        this.appName = appName;
    }
    public spritOSPackageManager(String cmd, String path, String[] appName) {
        this.cmd = cmd;
        this.appName = appName;
        this.path = path;
    }
    public void run() {
        if(this.cmd == "install") {
            spamInstall install = new spamInstall(this.appName);
            install.start();
        } else if(this.cmd == "uninstall") {
            spamUninstall unInstall = new spamUninstall(this.appName);
            unInstall.start();
        } else if(this.cmd == "upload") {
            spamUpload upload = new spamUpload(this.path, this.appName);
            upload.start();
        } else {
            System.out.println("");
        }
    }
}
class spamInstall extends Thread {
    String[] appName;
    public spamInstall(String[] appName) {
        this.appName = appName;
    }
    public void run() {
        try {
            int appAmount = 0;
            byte[] b = new byte[4194304];
            HttpURLConnection[] cnct = new HttpURLConnection[0];
            DataInputStream[] dataInStream = new DataInputStream[0];
            DataOutputStream[] dataOutStream = new DataOutputStream[0];
            int[] httpStatusCode = new int[0];
            int readByte;
            URL[] url = new URL[0];
            for (int i = 0; i < url.length; i++) {
                url[i] = new URL("https://ss1.xrea.com/mshome.s1009.xrea.com/download/spam/" + appName[i]);
                appAmount++;
            }
            for (int i = 0; i < appAmount; i++) {
                cnct[i] = (HttpURLConnection) url[i].openConnection();
                cnct[i].setAllowUserInteraction(false);
                cnct[i].setInstanceFollowRedirects(true);
                cnct[i].setRequestMethod("GET");
                cnct[i].connect();
                httpStatusCode[i] = cnct[i].getResponseCode();
                if (httpStatusCode[i] != HttpURLConnection.HTTP_OK) {
                    throw new Exception();
                }
                dataInStream[i] = new DataInputStream(cnct[i].getInputStream());
                dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(spritOSCore.getHomeDir() + "\\bin\\" + appName + ".spr")));
                while (true) {
                    if (-1 == (readByte = dataInStream[i].read(b))) break;
                    dataOutStream[i].write(b, 0, readByte);
                    dataInStream[i].close();
                    dataOutStream[i].close();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
class spamUninstall extends Thread {
    String[] appName;
    public spamUninstall(String[] appName) {
        this.appName = appName;
    }
    public void run() {
        try {
            for (int i = 0; i > appName.length; i++) {
                File app;
                app = new File(spritOSCore.getHomeDir() + "\\bin\\" + appName + ".spr");
                if(app.exists()) {
                    if(app.delete()) {
                        spritLog("spritos_spam:spamUninstall: Successfully " + appName + " delete.");
                    } else {
                        spritLog("spritos_spam:spamUninstall: Failed " + appName + " delete.");
                        continue;
                    }
                } else {
                    spritLog("spritos_spam:spamUninstall: Application not found.");
                    continue;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
class spamUpload extends Thread {
    String[] appName;
    String path;
    public spamUpload(String path, String[] appName) {
        this.path = path;
        this.appName = appName;
    }
    public void run() {
        try {
            File app;
            app = new File(spritOSCore.getHomeDir() + path + appName + ".spr");
            if(!app.exists()) {
                throw new Exception();
            }
            URL url = new URL("https://ss1.xrea.com/mshome.s1009.xrea.com/download/spam/upload.php?app=" + appName + ".spr");
            HttpURLConnection cnct = (HttpURLConnection) url.openConnection();
            cnct.setAllowUserInteraction(false);
            cnct.setInstanceFollowRedirects(true);
            cnct.setRequestMethod("POST");
            cnct.connect();
            int httpStatusCode = cnct.getResponseCode();
            if (httpStatusCode != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }
            DataInputStream dataInStream = new DataInputStream(new BufferedInputStream(new FileInputStream(app)));
            DataOutputStream dataOutStream = new DataOutputStream(cnct.getOutputStream());
            byte[] b = new byte[4096];
            int writeByte = 0;
            while(-1 != (writeByte = dataInStream.read(b))) {
                dataOutStream.write(b, 0, writeByte);
            }
            dataOutStream.close();
            dataInStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}