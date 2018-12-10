package days.Seven;

import java.util.TreeSet;

public class CommandStep {
    public String stepID;
    public TreeSet<String> nextSteps;

    CommandStep(String stepID){
        this.stepID = stepID;
        nextSteps = new TreeSet<>();
    }

    public void addNextStep(String step){
        nextSteps.add(step);
    }


}
