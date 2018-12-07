package days.four;

import org.javatuples.Pair;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GuardParser {

    BufferedReader br;

    public GuardParser(File f){
        try{
            br = new BufferedReader(new FileReader(f));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LogData> parse(){
        return this.parse(br);
    }

    private ArrayList<LogData> parse(BufferedReader br){
        ArrayList<LogData> returnList = new ArrayList<>();
        String line = "";
        Pattern numPattern = Pattern.compile("^\\[(\\d{4}-\\d{1,}-\\d{1,} (\\d{2}:\\d{2}))]\\s*(.*)$");

        TreeSet<Pair<LocalDateTime, LogData>> raw = new TreeSet<Pair<LocalDateTime, LogData>>((o1, o2) -> {
            return o1.getValue0().compareTo(o2.getValue0());
        });
        try{
            while((line = br.readLine()) != null){
                Matcher m = numPattern.matcher(line);
                m.matches();
                MatchResult mr = m.toMatchResult();
                String timeString = mr.group(1).replace(' ', 'T');
                LocalDateTime date = LocalDateTime.parse(timeString);
                LogAction action = assignAction(mr.group(3));
                Integer id;
                if(action == LogAction.SHIFT_CHANGE) {
                    id = assignId(mr.group(3));
                } else {
                    id = null;
                }
                LogData ld = new LogData(id, action, date);
                Pair<LocalDateTime, LogData> newPair = new Pair<>(date, ld);
                raw.add(newPair);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        raw = assignListIds(raw);
        returnList = raw.stream()
                .map((Pair<LocalDateTime, LogData> pair) ->  pair.getValue1() )
                .collect(Collectors.toCollection(ArrayList::new));

        return returnList;
    }

    private LogAction assignAction(String actionString) {
        if(actionString.contains("falls")) {
            return LogAction.SLEEP;
        } else if(actionString.contains("wakes")){
            return LogAction.WAKE_UP;
        } else if(actionString.contains("Guard")) {
            return LogAction.SHIFT_CHANGE;
        } else {
            return LogAction.ERROR;
        }
    }

    private int assignId(String in){
        String num = in.replaceAll("[^0-9?!\\.]","");
        int result = Integer.parseInt(num);
        return result;
    }

    private TreeSet<Pair<LocalDateTime, LogData>>  assignListIds(TreeSet<Pair<LocalDateTime, LogData>> treeSet) {
        for(Pair<LocalDateTime, LogData> setElement: treeSet) {
            if(setElement.getValue1().id == null) {
                setElement.getValue1().id = treeSet.lower(setElement).getValue1().id;
            }
        }
        return treeSet;
    }
}
