package days.four;

import days.three.DayThreeMain;

import java.io.File;
import java.net.URL;
import java.util.*;

public class DayFourMain {
    public static void main(String[] args) {
        ClassLoader cl = DayThreeMain.class.getClassLoader();
        URL pathURL = cl.getResource("dayFour/real.txt");
        File ff = new File(pathURL.getPath());
        GuardsMenager gm = new GuardsMenager(ff);
        ArrayList<LogData> ld = gm.getParsedLog();
        HashMap<Integer, Guard> comLog = gm.composeLog(ld);

        comLog.keySet().forEach(key -> {
            Guard g = comLog.get(key);
            int totalSleep = g.log.stream()
                    .mapToInt(gl -> gl.minutesSleep)
                    .sum();
            g.totalSleepTime = totalSleep;
        });

        Iterator<Integer> it =  comLog.keySet().iterator();

        int localMax = 0;
        int idMax = 0;

        while(it.hasNext()){
            Guard guard = comLog.get(it.next());
            if(guard.totalSleepTime > localMax){
                localMax = guard.totalSleepTime;
                idMax = guard.id;
            }
        }

        int maxMinSleep = Arrays.stream(comLog.get(idMax).sleepArrayCnt).max().orElse(-100);

        int locMinVal = 0;
        int locMinIdx = 0;
        int[] array = comLog.get(idMax).sleepArrayCnt;
        for(int i =0; i < array.length ; i++){
            if(array[i] > locMinVal){
                locMinIdx = i;
                locMinVal = array[i];
            }
        }


        System.out.println( locMinIdx * idMax);

        it =  comLog.keySet().iterator();
        int secondIdMax = 0;
        int secondPosMax = 0;
        int secondPosVal = 0;

        while(it.hasNext()){
            Guard guard = comLog.get(it.next());
            for(int i = 0; i< guard.sleepArrayCnt.length; i++){
                if(guard.sleepArrayCnt[i] > secondPosVal){
                    secondPosVal = guard.sleepArrayCnt[i];
                    secondPosMax = i;
                    secondIdMax = guard.id;
                }
            }
        }

        System.out.println( secondIdMax * secondPosMax);

    }
}
