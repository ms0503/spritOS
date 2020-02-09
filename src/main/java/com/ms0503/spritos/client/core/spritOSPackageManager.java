package com.ms0503.spritos.client.core;

/**
 * spritOS Package Manager (spam)
 * @author ms0503
 * @version 1.3.0
 * @since 1.1.0
 */

import com.ms0503.lib.Delete;
import com.ms0503.lib.Zip;
import com.ms0503.spritos.common.core.spritOSCore;
import com.ms0503.spritos.common.core.spritOSLogCore;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class spritOSPackageManager extends Thread {
    String cmd;
    String[] appName;
    public spritOSPackageManager(String cmd, String[] appName) {
        this.cmd = cmd;
        this.appName = appName;
        this.start();
    }
    public void run() {
        if(cmd == "install") {
            new spamInstall(appName);
        } else if(cmd == "uninstall") {
            new spamUninstall(appName);
        } else if(cmd == "upload") {
            new spamUpload(appName);
        } else {
            System.out.println("Command not found.");
        }
    }
}
class spamInstall extends Thread {
    String[] appName;
    public spamInstall(String[] appName) {
        this.appName = appName;
        this.start();
    }
    public void run() {
        try {
            int appAmount = appName.length;
            byte[] b = new byte[4194304];
            HttpURLConnection[] cnct = new HttpURLConnection[0];
            DataInputStream[] dataInStream = new DataInputStream[0];
            DataOutputStream[] dataOutStream = new DataOutputStream[0];
            int readByte;
            URL[] url = new URL[0];
            for(int i = 0; i < appAmount; i++) {
                url[i] = new URL("https://ss1.xrea.com/mshome.s1009.xrea.com/download/spam/" + appName[i] + ".zip");
                cnct[i] = (HttpURLConnection) url[i].openConnection();
                cnct[i].setAllowUserInteraction(false);
                cnct[i].setInstanceFollowRedirects(true);
                cnct[i].setRequestMethod("GET");
                cnct[i].connect();
                if (cnct[i].getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return;
                }
                dataInStream[i] = new DataInputStream(cnct[i].getInputStream());
                dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(spritOSCore.getBinDir() + appName[i] + ".zip")));
                while(true) {
                    if(-1 == (readByte = dataInStream[i].read(b))) break;
                    dataOutStream[i].write(b, 0, readByte);
                    dataInStream[i].close();
                    dataOutStream[i].close();
                    Zip.unzip(spritOSCore.getBinDir() + appName[i] + ".zip", spritOSCore.getBinDir() + appName[i]);
                    Delete.delete(new File(appName[i] + ".zip"));
                }
                System.out.println("Installed " + appName[i] + " .");
                new spritOSLogCore("spritos_spam:spamInstall", "Installed " + appName[i] + " .");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
class spamUninstall extends Thread {
    String[] appName;
    File[] file;
    public spamUninstall(String[] appName) {
        this.appName = appName;
        this.start();
    }
    public void run() {
        for(int i = 0; ; i++) {
            file[i] = new File(spritOSCore.getBinDir() + appName[i]);
            Delete.delete(file[i]);
        }
    }
}
class spamUpload extends Thread {
    String[] appName;
    public spamUpload(String[] appName) {
        this.appName = appName;
        this.start();
    }
    public void run() {
        try {
            File[] app = new File[0];
            int appAmount = appName.length;
            HttpURLConnection[] cnct = new HttpURLConnection[0];
            DataInputStream[] dataInStream = new DataInputStream[0];
            DataOutputStream[] dataOutStream = new DataOutputStream[0];
            URL[] url = new URL[0];
            for(int i = 0; i < appAmount; i++) {
                url[i] = new URL("https://ss1.xrea.com/mshome.s1009.xrea.com/download/spam/upload.php?app=" + appName[i] + ".zip");
                app[i] = new File(spritOSCore.getBinDir() + appName[i] + ".zip");
                cnct[i] = (HttpURLConnection) url[i].openConnection();
                cnct[i].setAllowUserInteraction(false);
                cnct[i].setInstanceFollowRedirects(true);
                cnct[i].setRequestMethod("POST");
                cnct[i].connect();
                if (cnct[i].getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return;
                }
                dataInStream[i] = new DataInputStream(new BufferedInputStream(new FileInputStream(app[i])));
                dataOutStream[i] = new DataOutputStream(cnct[i].getOutputStream());
                byte[] b = new byte[4096];
                int writeByte = 0;
                while (-1 != (writeByte = dataInStream[i].read(b))) {
                    dataOutStream[i].write(b, 0, writeByte);
                }
                dataInStream[i].close();
                dataOutStream[i].close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}