package days.Seven;

import java.util.*;

public class CommandStepMenager {

    public TreeSet<String> availableSteps;
    public TreeSet<String> alreadyCompletedSteps;
    public CommandStep lastStep;
    public CommandStep firstStep;
    public TreeMap<String, CommandStep> commands;

    CommandStepMenager(TreeMap<String, CommandStep> commands){
        this.availableSteps = new TreeSet<>();
        this.alreadyCompletedSteps = new TreeSet<>();
        this.commands = new TreeMap<>(commands);
    }


    public void processList(CommandStep first, CommandStep last){
        lastStep = last;
        String initId = commands.get(first.stepID).stepID;
        String path = processStep(first, initId);
        System.out.println(path);

    }

    private String processStep(CommandStep current, String stepsPath) {
        if(current != lastStep){
            alreadyCompletedSteps.add(current.stepID);
            buildAvailableStepsList(current);
            String nextStepId = getNextStepId(current);
            if(nextStepId == null){
                System.out.println("Fuck");
            }
            CommandStep nextStepToProcess = commands.get(nextStepId);
            stepsPath += nextStepToProcess.stepID;
            return processStep(nextStepToProcess, stepsPath);
        }
        else {
            return stepsPath;
        }
    }

    private void buildAvailableStepsList(CommandStep current) {
        current.nextSteps.forEach((key, value) -> {
            if(!alreadyCompletedSteps.contains(key)){
                this.availableSteps.add(key);
            }
        });
    }

    private boolean allPrescedingDone(CommandStep candidate) {

        TreeSet<String> allPreviousSteps = new TreeSet<>();
        commands.forEach((key, val) -> {
            commands.get(key).nextSteps.forEach((nextKey, nextVal) ->{
                if(nextKey.equals(candidate.stepID)) {
                    allPreviousSteps.add(key);
                }
            });
        });

        boolean retVal = true;
        Iterator<String> iter = allPreviousSteps.iterator();
        while(iter.hasNext()) {
            if(!alreadyCompletedSteps.contains(iter.next())) {
                retVal = false;
                break;
            }
        }
        return retVal;
    }

    private String getNextStepId(CommandStep current) {

        Iterator<String> availableStepsIter = availableSteps.iterator();
        String proposedNextId = null;
        while(availableStepsIter.hasNext()){
            String chackedId = availableStepsIter.next();
            if(this.allPrescedingDone(commands.get(chackedId))){
                proposedNextId = chackedId;
                availableSteps.remove(proposedNextId);
                break;
            }
        }
        return  proposedNextId;
    }

    public void addFirstSteps(Set<String> FirstsIds){
        FirstsIds.forEach(id -> {
            this.availableSteps.add(id);
        });
    }

}
