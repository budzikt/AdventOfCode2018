package days.Seven;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DaySevenMain {
    public static void main(String[] args) {
        ClassLoader cl = DaySevenMain.class.getClassLoader();
        URL pathURL = cl.getResource("daySeven/real.txt");
        File ff = new File(pathURL.getPath());

        TreeMap<String, CommandStep> commands = new TreeMap<>();

        Set<String> allCommandsIds = new TreeSet<>();
        Set<String> nonFirstSet = new TreeSet<>();
        Set<String> nonLastSet = new TreeSet<>();


        try {
            BufferedReader br = new BufferedReader(new FileReader(ff));
            String line;
            Pattern pattern = Pattern.compile("^Step ([A-Z]) must be finished before step ([A-Z]).*$");
            int idx = 0;

            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                m.matches();
                MatchResult mr = m.toMatchResult();
                String currentStep = mr.group(1);
                String nextStep = mr.group(2);

                allCommandsIds.add(currentStep);
                allCommandsIds.add(nextStep);

                nonLastSet.add(currentStep);
                nonFirstSet.add(nextStep);

                commands.computeIfPresent(currentStep, (stepId, command) -> {
                    CommandStep nestStep = new CommandStep(nextStep);
                    command.addNextStep(nestStep);
                    return command;
                });
                commands.computeIfAbsent(currentStep, (stepId) -> {
                    CommandStep cs = new CommandStep(stepId);
                    CommandStep nestStep = new CommandStep(nextStep);
                    cs.addNextStep(nestStep);
                    return cs;
                });


            }


            Set<String> firstSet = new TreeSet<>(allCommandsIds);
            firstSet.removeAll(nonFirstSet);
            String idFirst = firstSet.iterator().next();
            if(firstSet.size() > 1){
                firstSet.remove(firstSet.iterator().next());
            }


            Set<String> lastSet = new TreeSet<>(allCommandsIds);
            lastSet.removeAll(nonLastSet);
            String idLast = lastSet.iterator().next();
            commands.put(idLast, new CommandStep(idLast));
            CommandStep firstCommand = commands.get(idFirst);
            CommandStep lastCommand = commands.get((idLast));

            System.out.println("Parsed steps, up and running");

            CommandStepMenager csm = new CommandStepMenager(commands);
            csm.addFirstSteps(firstSet);
            csm.processList(firstCommand, lastCommand);



            /*Part 2*/
            TimedCommandsMenager tcsm = new TimedCommandsMenager(commands, 5, 60);
            firstSet.add("J");
            tcsm.addFirstSteps(firstSet);
            tcsm.processList(firstCommand, lastCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
