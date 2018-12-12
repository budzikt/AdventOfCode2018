package days.Seven;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class CommandStepMenager {

    public TreeSet<String> availableSteps;
    public TreeSet<String> alreadyCompletedSteps;
    public CommandStep lastStep;

    CommandStepMenager(){
        this.availableSteps = new TreeSet<>();
        this.alreadyCompletedSteps = new TreeSet<>();
    }

    public void processList(TreeMap<String, CommandStep> commands, CommandStep first, CommandStep last){
        lastStep = last;
        String initId = commands.get(first.stepID).stepID;
        String path = processStep(commands, first, initId);
        System.out.println(path);

    }

    private String processStep(TreeMap<String, CommandStep> commands, CommandStep current, String stepsPath) {
        CommandStep nextStep = null;
        if(commands.get(current.stepID).nextSteps.size() > 0 && current != lastStep){
              buildAvailableStepsList(current);
            CommandStep nextStepToProcess = commands.get(getNextStepId(current));
            stepsPath += nextStepToProcess.stepID;
            return processStep(commands, nextStepToProcess, stepsPath);
        }
        else {
            return stepsPath;
        }
    }

    private void buildAvailableStepsList(CommandStep current) {
        current.nextSteps.forEach((key, value) -> {
            this.availableSteps.add(key);
        });

    }

    private String getNextStepId(CommandStep current) {

        Iterator<String> itr = availableSteps.iterator();
        String proposedNext = itr.next();
        if(proposedNext.equals(lastStep.stepID) && availableSteps.size() > 1) {
            proposedNext =  itr.next();
            availableSteps.remove(proposedNext);
        } else {
            availableSteps.remove(proposedNext);
        }
        return  proposedNext;
    }

}
