package days.Seven;

import java.util.TreeMap;
import java.util.TreeSet;

public class TimedCommandStep extends CommandStep {

    public int timeToComplete;
    public static int baseCommandTime;

    TimedCommandStep(String stepID, int baseTime) {
        super(stepID);
        TimedCommandStep.baseCommandTime = baseTime;
        this.timeToComplete = Character.toLowerCase(stepID.charAt(0)) + TimedCommandStep.baseCommandTime;
    }

    TimedCommandStep(CommandStep command, int baseTime) {
        super(command.stepID, command.nextSteps);
        TimedCommandStep.baseCommandTime = baseTime;
        int val = (int)(stepID.charAt(0)) - 64;
        this.timeToComplete = val + TimedCommandStep.baseCommandTime;
    }

    public void calculateProcessTime() {
        this.timeToComplete = Character.toLowerCase(stepID.charAt(0)) + TimedCommandStep.baseCommandTime;
    }
}
