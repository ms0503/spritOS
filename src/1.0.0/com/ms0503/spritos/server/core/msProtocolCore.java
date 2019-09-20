package com.ms0503.spritos.server.core;

import com.ms0503.spritos.common.core.spritOSLogCore;
import java.io.*;
import java.net.*;
import java.util.*;

/**@author ms0503*/

@Program(id="spritOS_MPC", name="msProtocolCore", version="1.0.0", author="ms0503")
public class msProtocolCore {
    public int encoding(int len, String str) {
        msProtocolCore sv = new msProtocolCore();
        try {
            ServerSocket ss = new ServerSocket(7292);
            spritLog("spritOS_MPC: Waiting now ...");
            while(true) {
                try {
                    Socket sc = ss.accept();
                    spritLog("spritOS_MPC: Welcome!");
                    ConnectToClient cc = new ConnectToClient(sc);
                    cc.start();
                } catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class ConnectToClient extends Thread {
    private Socket sc;
    private BufferedReader br;

    public Client(Socket s) {
        sc = s;
    }

    public void run() {
        try {
            br = new bufferedReader(new InputStreamReader(sc.getInputStream()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                
            }
        }
    }
}