package days.four;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GuardsMenager {
    public ArrayList<LogData> parsedLog;
    public HashMap<Integer, Guard> guardsLogs;
    public GuardParser parser;

    public GuardsMenager(){
        this.parsedLog = new ArrayList<>();
        this.guardsLogs = new HashMap<>();
    }

    public GuardsMenager(File f){
        this();
        this.parser = new GuardParser(f);
        parsedLog = parser.parse();
    }

    public ArrayList<LogData> getParsedLog() {
        return parsedLog;
    }

    public HashMap<Integer, Guard> composeLog(ArrayList<LogData> sortedLog){
        HashMap<Integer, Guard> returnSet = new HashMap<>();
        sortedLog.forEach(logData -> {
            if(returnSet.containsKey(logData.id)){
                returnSet.get(logData.id).addLogEntry(logData);
            }else{
                returnSet.put(logData.id, new Guard(logData.id));
            }
        });
        return returnSet;
    }

}
