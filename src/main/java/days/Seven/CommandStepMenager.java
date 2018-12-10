package days.Seven;

import java.util.HashMap;

public class CommandStepMenager {

    static public void processList(HashMap<String, CommandStep> commands, String first, String last){
        commands.get(first);
        String path = CommandStepMenager.processStep(commands, first, first);
        System.out.println(path);

    }

    static  public String processStep(HashMap<String, CommandStep> commands, String current, String path) {
        String returnStep = null;
        if(commands.get(current).nextSteps.size() > 0){
            returnStep = commands.get(current).nextSteps.first();
            path += returnStep;
            return processStep(commands, returnStep, path);
        }
        else {
            return path;
        }

    }
}
