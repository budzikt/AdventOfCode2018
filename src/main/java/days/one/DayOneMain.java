package days.one;

import days.one.doubleDetector.DoubleDetector;
import days.one.parser.StringFreqParser;

import java.util.stream.Stream;

public class DayOneMain {
    public static void main(String[] args) {
        FreqCounter fq = new FreqCounter();
        fq.setInputParser(new StringFreqParser());
        fq.setDoubleDetector(new DoubleDetector());
        try {
            fq.parse(InputData.real);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] res = new Integer[0];
        res = fq.getFreqList();


        Integer result = Stream.of(res).reduce(0, (in, acc) -> in+acc);
        System.out.println(result);

        while(true) {
            fq.calcSeriesDrift();
            if(fq.getDoubled() != null) {
                System.out.println("Doubled drift found:" + fq.getDoubled());
                break;
            }
        }

    }

}
