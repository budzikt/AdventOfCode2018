package days.one;

import days.one.doubleDetector.DoubleDetector;
import days.one.parser.IFreqParser;

import java.util.ArrayList;
import java.util.HashSet;

public class FreqCounter {

    private ArrayList<Integer> freqList;
    private ArrayList<Integer> freqDrift;
    private HashSet<Integer> seenFreqs;

    private IFreqParser parser;
    private DoubleDetector doubleDetector;
    private Integer doubledVal;

    public FreqCounter() {
        seenFreqs = new HashSet<>();
    }

    public FreqCounter(IFreqParser parser, DoubleDetector dd) {
        this();
        this.parser = parser;
        doubleDetector = dd;
    }

    public void setInputParser(IFreqParser parser) {
        this.parser = parser;
    }

    public void setDoubleDetector(DoubleDetector doubleDet) {
        this.doubleDetector = doubleDet;
    }

    public void parse(String input) throws Exception {
        try {
        freqList = this.parser.parseInput(input);
        } catch(Exception e) {
            throw new Exception("Unable to parse input");
        }
    }

    public void calcSeriesDrift() {
        if(this.freqDrift == null) {
            initDrift();
        }
        for(int i = 0; i<freqList.size(); i++) {
            Integer newFreqDrift = freqDrift.get(freqDrift.size() - 1) + freqList.get(i);
            freqDrift.add(newFreqDrift);
            if(doubleDetector.isInSet(seenFreqs, newFreqDrift, true)) {
                this.doubledVal = newFreqDrift;
                break;
            }
        }
    }

    private void initDrift() {
        this.freqDrift = new ArrayList<>();
        this.freqDrift.add(0);
    }


    public Integer getDoubled() {
        return this.doubledVal;
    }

    public Integer[] getFreqList(){
        Integer[] retArray = new Integer[freqList.size()];
        return freqList.toArray(retArray);
    }
}
