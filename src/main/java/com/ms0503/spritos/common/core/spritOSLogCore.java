package com.ms0503.spritos.common.core;

/**
 * spritOS Logger
 * @author ms0503
 * @version 1.3.0
 * @since 1.0.0
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class spritOSLogCore extends Thread {
    private final String id;
    private final String msg;
    /**
     * spritOS Logger
     * @param id AppID
     * @param msg Message
     */
    public spritOSLogCore(String id, String msg) {
        this.id = id;
        this.msg = msg;
        this.start();
    }
    @Override
    public void run() {
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            FileWriter fw;
            PrintWriter writer;
            if(!new File(spritOSCore.getLogDir() + sdf.format(now.getTime()) + ".log").exists()) {
                fw = new FileWriter(spritOSCore.getLogDir() + sdf.format(now.getTime()) + ".log", false);
            } else {
                fw = new FileWriter(spritOSCore.getLogDir() + sdf.format(now.getTime()) + ".log", true);
            }
            writer = new PrintWriter(new BufferedWriter(fw));
            writer.println(id + ": " + msg);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}