package com.ms0503.spritos.server.core;

import com.ms0503.spritos.common.annotation.spritSoft;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static com.ms0503.spritos.common.core.spritOSLogCore.spritLog;

@spritSoft(id="spritOS_spam", name="spritOSPackageManager", version="1.0.0", requireOS = "this", author="ms0503")
public class spritOSPackageManager {
    public static void spamInstall(@NotNull String[] appName) throws Exception {
        int appAmount = 0;
        byte[] b = new byte[4194304];
        HttpURLConnection[] cnct = new HttpURLConnection[0];
        DataInputStream[] dataInStream = new DataInputStream[0];
        DataOutputStream[] dataOutStream = new DataOutputStream[0];
        int[] httpStatusCode = new int[0];
        int readByte = 0;
        URL[] url = new URL[0];
        for(int i = 0; i < url.length; i++) {
            url[i] = new URL("http://ms0503hp.ddo.jp/download/spam/" + appName[i]);
            appAmount++;
        }
        for(int i = 0; i < appAmount; i++) {
            try {
                cnct[i] = (HttpURLConnection) url[i].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cnct[i].setAllowUserInteraction(false);
            cnct[i].setInstanceFollowRedirects(true);
            try {
                cnct[i].setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                cnct[i].connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpStatusCode[i] = cnct[i].getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(httpStatusCode[i] != HttpURLConnection.HTTP_OK) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                dataInStream[i] = new DataInputStream(cnct[i].getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("%USERPROFILE%\\spritOS\\bin\\" + appName + ".spr")));
            } catch(IOException e) {
                e.printStackTrace();
                try {
                    dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("~/spritOS/bin/" + appName + ".spr")));
                } catch(IOException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            readByte = 0;
            while(true) {
                try {
                    if (!(-1 != (readByte = dataInStream[i].read(b)))) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataOutStream[i].write(b, 0, readByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                dataInStream[i].close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutStream[i].close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void spamUninstall(@NotNull String[] appName) throws Exception {
        for(int i = 0; i > appName.length; i++) {
            File app = new File("%USERPROFILE%\\spritOS\\bin\\" + appName + ".spr");
            if(app.exists()) {
                if(app.delete()) {
                    spritLog("spritos_spam:spamUninstall: 正常に" + appName + "は削除されました。");
                } else {
                    spritLog("spritos_spam:spamUninstall: " + appName + "は削除できませんでした。");
                    continue;
                }
            } else {
                app = new File("~/spritOS/bin/" + appName + ".spr");
                if(app.exists()) {
                    if(app.delete()) {
                        spritLog("spritos_spam:spamUninstall: 正常に" + appName + "は削除されました。");
                    } else {
                        spritLog("spritos_spam:spamUninstall: " + appName + "は削除できませんでした。");
                        continue;
                    }
                } else {
                    spritLog("spritos_spam:spamUninstall: アプリがありません。");
                    continue;
                }
            }
        }
    }
    public static void spamUpload(@NotNull String appName, @NotNull String path) throws Exception {
        File app;
        app = new File("%USERPROFILE%\\spritOS\\" + path + appName + ".spr");
        if(!app.exists()) {
            app = new File("~/spritOS/" + path + appName + ".spr");
            if(!app.exists()) {
                throw new Exception();
            }
        }
        URL url = new URL("http://ms0503hp.ddo.jp/download/spam/upload.php?app=" + appName + ".spr");
        HttpURLConnection cnct = (HttpURLConnection) url.openConnection();
        cnct.setAllowUserInteraction(false);
        cnct.setInstanceFollowRedirects(true);
        cnct.setRequestMethod("POST");
        cnct.connect();
        int httpStatusCode = cnct.getResponseCode();
        if(httpStatusCode != HttpURLConnection.HTTP_OK) {
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
    }
}