package com.ms0503.spritos.server.core;

import com.ms0503.spritos.common.annotation.spritSoft;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.ms0503.spritos.common.core.spritOSLogCore.spritLog;

@spritSoft(id="spritOS_spam", name="spritOSPackageManager", version="1.0.0", requireOS = "this", author="ms0503")
public class spritOSPackageManager {
    public static void spamInstall(String[] appName) throws Exception {
        int appAmount = 0;
        byte[] b = new byte[4194304];
        HttpURLConnection[] cnct = new HttpURLConnection[0];
        DataInputStream[] dataInStream = new DataInputStream[0];
        DataOutputStream[] dataOutStream = new DataOutputStream[0];
        int[] httpStatusCode = new int[0];
        int readByte = 0;
        URL[] url = new URL[0];
        for (int i = 0; i < url.length; i++) {
            url[i] = new URL("https://github.com/ms0503/spam/" + appName[i]);
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
            try {
                dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("%USERPROFILE%\\spritOS\\bin\\" + appName + ".spr")));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    dataOutStream[i] = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("~/spritOS/bin/" + appName + ".spr")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            readByte = 0;
            while (-1 != (readByte = dataInStream[i].read(b))) {
                dataOutStream[i].write(b, 0, readByte);
            }
            dataInStream[i].close();
            dataOutStream[i].close();
        }
    }
    public static void spamUninstall(@NotNull String[] appName) throws Exception {
        for(int i = 0; i > appName.length; i++) {
            File app = new File("%USERPROFILE%\\spritOS\\bin\\" + appName + ".spr");
            if (app.exists()) {
                if (app.delete()) {
                    spritLog("spritos_spam:spamUninstall: 正常に" + appName + "は削除されました。");
                } else {
                    spritLog("spritos_spam:spamUninstall: " + appName + "は削除できませんでした。");
                    continue;
                }
            } else {
                app = new File("~/spritOS/bin/" + appName + ".spr");
                if (app.exists()) {
                    if (app.delete()) {
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
}