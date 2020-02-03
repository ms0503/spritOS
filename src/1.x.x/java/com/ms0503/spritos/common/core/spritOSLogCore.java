package com.ms0503.spritos.common.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class spritOSLogCore {
    public static void spritLog(String msg) throws Exception {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FileWriter logFile = new FileWriter("%USERPROFILE%\\spritOS\\log\\" + sdf.format(now.getTime()) + ".log", true);
            PrintWriter writer = new PrintWriter(new BufferedWriter(logFile));
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                FileWriter logFile = new FileWriter("~/spritOS/log/" + sdf.format(now.getTime()) + ".log", true);
                PrintWriter writer = new PrintWriter(new BufferedWriter(logFile));
                writer.println(msg);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
    }
}