package com.ms0503.spritos.common.core;

/**
 * spritOS Logger
 * @author ms0503
 * @version 1.3.1
 * @since 1.0.0
 */

import org.apache.log4j.Logger;

public class spritOSLogCore extends Thread {
    private final String lv;
    public static Logger log = Logger.getLogger(spritOSLogCore.class);
    private final String msg;
    /**
     * spritOS Logger
     * @param lv LogLevel
     * @param msg Message
     */
    public spritOSLogCore(String lv, String msg) {
        this.lv = lv;
        this.msg = msg;
        this.start();
    }
    @Override
    public void run() {
        switch(lv) {
            case "trace":
                log.trace(msg);
                break;
            case "debug":
                log.debug(msg);
                break;
            case "warn":
                log.warn(msg);
                break;
            case "error":
                log.error(msg);
                break;
            case "fatal":
                log.fatal(msg);
                break;
            default:
                log.info(msg);
        }
    }
}