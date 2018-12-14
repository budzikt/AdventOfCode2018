package days.Seven;

import java.util.*;

public class TimedCommandsMenager extends CommandStepMenager {

    public int numOfWOrkers;
    public int freeWorkers;
    public int totalTime;
    public TreeMap<String, Integer> pendingSteps;

    public TimedCommandsMenager(TreeMap<String, CommandStep> commands, int workers, int timeFactor) {
        TreeMap<String, CommandStep> timedCommands = new TreeMap<>();
        commands.forEach((k,v) -> {
            CommandStep tcs = new TimedCommandStep(v,timeFactor);
            timedCommands.put(k, tcs);
        });
        this.commands = timedCommands;
        this.pendingSteps = new TreeMap<>();
        numOfWOrkers = workers;
        freeWorkers = workers;
        totalTime = 0;
    }

    @Override
    public void processList(CommandStep first, CommandStep last){
        lastStep = last;

        TreeSet<String> availableStepsCopy = new TreeSet<>(availableSteps);
        Iterator<String> availableStepsIterator = availableStepsCopy.iterator();


        while(availableStepsIterator.hasNext() && freeWorkers > 0){
            CommandStep initialActiveStep = commands.get(availableStepsIterator.next());
            pendingSteps.put(initialActiveStep.stepID, ((TimedCommandStep)initialActiveStep).timeToComplete);
            availableSteps.remove(initialActiveStep.stepID);
            freeWorkers--;
        }

        String initId = ""; //commands.get(first.stepID).stepID;
        String path = processStep(initId);
        System.out.println(path);
    }


    protected String processStep(String stepsPath) {
        if(availableSteps.size() == 0 && alreadyCompletedSteps.size() == commands.size()) {
            return stepsPath;
        }
        this.totalTime++;
        System.out.println(totalTime);
        LinkedHashSet<String> completedSteps = handlePendingSteps();
        if(completedSteps.size() > 0){
            completedSteps.forEach((k) -> {
                CommandStep justCompletedStep = commands.get(k);
                buildAvailableStepsList(justCompletedStep);
            });
            alreadyCompletedSteps.addAll(completedSteps);
            Iterator<String> compIter = completedSteps.iterator();
            while(compIter.hasNext()){
                stepsPath += compIter.next();
            }
        }
        if(freeWorkers > 0){
            while(freeWorkers > 0){
                if(availableSteps.size() > 0){
                    String nextId = getNextStepId();
                    if(nextId == null){
                        System.out.println(totalTime);
                        break;
                    }
                    else{
                        pendingSteps.put(nextId, ((TimedCommandStep)commands.get(nextId)).timeToComplete);
                        freeWorkers--;
                    }
                }else{
                    break;
                }
            }
            return processStep(stepsPath);

        }else{
            return processStep(stepsPath);
        }

    }

    private LinkedHashSet<String> handlePendingSteps(){
        LinkedHashSet<String> stepsDone = new LinkedHashSet<>();

        TreeMap<String, Integer> pendingsCopy = new TreeMap<>(pendingSteps);
        TreeMap<String, Integer> keysToAdd = new TreeMap<>();
        Set<String> keysToRemove = new HashSet<>();

        Iterator<String> iter = pendingSteps.keySet().iterator();
        while(iter.hasNext()){
            String k = iter.next();
            Integer timeLeft = pendingSteps.get(k);
            if(--timeLeft <= 0){
                //pendingSteps.remove(k);
                keysToRemove.add(k);
                stepsDone.add(k);
                freeWorkers++;
            } else {
                //pendingSteps.put(k, timeLeft);
                keysToAdd.put(k, timeLeft);
            }
        }


        keysToRemove.forEach(k -> {
            pendingSteps.remove(k);
        });
        keysToAdd.forEach((k,v) -> {
            pendingSteps.put(k,v);
        });

        return stepsDone;

    }
}
