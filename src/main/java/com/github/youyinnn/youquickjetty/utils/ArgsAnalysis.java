package com.github.youyinnn.youquickjetty.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: youyinnn
 */
public class ArgsAnalysis {

    private HashMap<String,String> commandValuesMap = new HashMap<>();
    private ArrayList<String> supportCmd = new ArrayList<>();

    public ArgsAnalysis(String[] args, ArrayList<String> supportCmd) {

        this.supportCmd = supportCmd;

        for (int i = 0 ; i < args.length ; i += 2) {
            if (args[i].contains("-")) {
                String cmd = args[i].substring(1);
                if (isSupportCmd(cmd)) {
                    commandValuesMap.put(cmd,args[i+1]);
                } else {
                    System.out.println("Nonsupport cmd : [ "+cmd+" ] .");
                }
            }
        }

    }

    public String getValue(String cmd) {
        return commandValuesMap.get(cmd);
    }

    public boolean hasCommand(String cmd) {
        return commandValuesMap.containsKey(cmd);
    }

    private boolean isSupportCmd(String cmd) {
        return supportCmd.contains(cmd);
    }

    public HashMap<String, String> getCommandValuesMap() {
        return commandValuesMap;
    }
}
