package days.two;

import days.two.labelReader.LabelReader;

import java.io.File;
import java.io.Reader;
import java.net.URL;

public class DayTwoMain {
    public static void main(String[] args) {

        ClassLoader cl = DayTwoMain.class.getClassLoader();
        URL pathURL = cl.getResource("dayTwo/real.txt");
        File ff = new File(pathURL.getPath());
        if(ff.exists() && !ff.isDirectory()) {
            System.out.println("Exist");
            LabelReader lr = new LabelReader(ff);
            long twoTimes = lr.getCounts(2);
            long threeTimes = lr.getCounts(3);
            long res = twoTimes * threeTimes;
            lr.findDiff();
            System.out.println(res);
        } else {
            System.out.println("Missing");
        }


    }
}
