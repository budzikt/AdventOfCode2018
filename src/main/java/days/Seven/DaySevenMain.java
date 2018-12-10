package days.Seven;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaySevenMain {
    public static void main(String[] args) {
        ClassLoader cl = DaySevenMain.class.getClassLoader();
        URL pathURL = cl.getResource("daySeven/test.txt");
        File ff = new File(pathURL.getPath());
        HashMap<String, CommandStep> commands = new HashMap<>();
        Set<String> allCommands = new HashSet<>();
        Set<String> nonFirstCommands = new HashSet<>();
        Set<String> nonLastCommands = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ff));
            String line;
            Pattern pattern = Pattern.compile("^Step ([A-Z]) must be finished before step ([A-Z]).*$");
            int idx = 0;
            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                m.matches();
                MatchResult mr = m.toMatchResult();
                String step = mr.group(1);
                String next = mr.group(2);

                commands.computeIfPresent(step, (stepId, command) -> {
                    command.addNextStep(next);
                    return command;
                });
                commands.computeIfAbsent(step, (stepId) -> {
                    CommandStep cs = new CommandStep(stepId);
                    cs.addNextStep(next);
                    return cs;
                });
                allCommands.add(step);
                allCommands.add(next);
                nonFirstCommands.add(next);
                nonLastCommands.add(step);
            }
            HashSet<String> first = new HashSet<>(allCommands);
            HashSet<String> last = new HashSet<>(allCommands);

            first.removeAll(nonFirstCommands);
            last.removeAll(nonLastCommands);

            String firstStr = (String)first.toArray()[0];
            String lastStr = (String)last.toArray()[0];

            System.out.println("");

            CommandStepMenager.processList(commands, firstStr, lastStr);

        } catch (Exception e) {

        }
    }
}
