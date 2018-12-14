package days.Seven;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class CommandStep implements Comparable<CommandStep>{
    public String stepID;

    public TreeMap<String, CommandStep> nextSteps;



    CommandStep(String stepID){
        this.stepID = stepID;
        nextSteps = new TreeMap<>();
    }

    CommandStep(String stepID, TreeMap<String, CommandStep> nextSteps){
        this.stepID = stepID;
        this.nextSteps = nextSteps;
    }

    public void addNextStep(CommandStep step){
        nextSteps.put(step.stepID, step);
    }


    @Override
    public int compareTo(CommandStep o) {
        return this.stepID.compareTo(o.stepID);
    }

    public static Comparator<CommandStep> getComperator() {
        return (o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            } else {
                return o1.compareTo(o2);
            }
        };
    }
}
