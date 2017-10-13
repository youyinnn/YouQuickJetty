package cn.youyinnn.youQuickJetty.utils;

import java.util.HashMap;

/**
 * @description:
 * @author: youyinnn
 * @date: 2017/10/13
 */
public class ArgsAnalysis {

    private HashMap<String,String> commandValuesMap = new HashMap<>();

    public ArgsAnalysis(String[] args) {

        for (int i = 0 ; i < args.length ; i += 2) {
            if (args[i].contains("-")) {
                commandValuesMap.put(args[i].substring(1),args[i+1]);
            }
        }

    }

    public String getValue(String cmd) {
        return commandValuesMap.get(cmd);
    }

    public boolean hasCommand(String cmd) {
        return commandValuesMap.containsKey(cmd);
    }
}
