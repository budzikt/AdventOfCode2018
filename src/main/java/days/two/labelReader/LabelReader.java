package days.two.labelReader;

import days.two.label.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class LabelReader {
    private BufferedReader reader;
    private List<Label> labels;

    public LabelReader() {
        labels = new ArrayList<Label>();
    }

    public LabelReader(File f) {
        this();
        try {
            reader = new BufferedReader( new FileReader(f));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
                labels.add(new Label(line));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void readLabels() {

    }

    public long getCounts(int value) {
        return labels.stream().filter(l -> l.countMap.containsValue(value)).count();
    }

    public String findDiff() {

        String ret = "";
        ArrayList<Label> foundLabels = new ArrayList<>();
        this.labels.forEach(l -> {
            if(foundLabels.size() == 0) {
                int idx = labels.indexOf(l);
                for(int i = 0; i < labels.size(); i++) {
                    if(idx == i) {
                        continue;
                    }
                    Label inspectLabel = labels.get(i);
                    if(compareTwoLabels(l, inspectLabel)){
                        foundLabels.add(l);
                        foundLabels.add(inspectLabel);
                        break;
                    }
                }
            }
        });
        if(foundLabels.size() == 2) {
            System.out.println("Got");
            ret = removeDiffer(foundLabels.get(0).label, foundLabels.get(1).label);
        }
        return ret;
    }

    private boolean compareTwoLabels(Label source, Label toCOmpare) {
        int diffs = 0;
        boolean ret = false;
        String sourceLabel = source.label;
        for(int i=0;i<sourceLabel.length(); i++) {
            if(sourceLabel.charAt(i) != toCOmpare.label.charAt(i)) {
                diffs++;
                if(diffs >= 2) {
                    break;
                }
            }
        }

        if(diffs == 1) {
            ret = true;
        }
        return ret;
    }

    private String removeDiffer(String one, String two) {
        String result = one;
        for(int i=0;i<one.length(); i++) {
            if(one.charAt(i) != two.charAt(i)) {
                result = new StringBuilder(one).deleteCharAt(i).toString();

            }
        }
        return result;
    }

}
