package days.four;

import java.util.ArrayList;
import java.util.HashMap;

public class Guard {
    public int wakeMark;
    public int id;
    public ArrayList<GuardLog> log;
    public int totalSleepTime;
    public int totalWakeUpTime;
    public int[] sleepArrayCnt;

    private Integer sleepMark;
    private LogAction previousAction;

    public Guard(int id){
        this.id = id;
        this.log = new ArrayList<>();
        sleepMark = 0;
        wakeMark = 0;
        sleepArrayCnt = new int[60];
        previousAction = LogAction.SHIFT_CHANGE;
    }

    public void addLogEntry(LogData ld) {
        if(ld.action == LogAction.SHIFT_CHANGE){

        }else if(ld.action == LogAction.SLEEP){
            sleepMark = ld.date.getMinute();
            if(previousAction == LogAction.SHIFT_CHANGE){

            }
        }else if(ld.action == LogAction.WAKE_UP){
            GuardLog logENtry = new GuardLog(ld.date.toLocalDate(), ld.date.getMinute() - sleepMark);
            for(int i = sleepMark; i<= ld.date.getMinute() - 1; i++){
                sleepArrayCnt[i]++;
            }
            this.log.add(logENtry);
            sleepMark = 0;
        }
    }
}
