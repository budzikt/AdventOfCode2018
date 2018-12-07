package days.three;

import days.three.claim.ClaimManager;

import java.io.File;
import java.net.URL;

public class DayThreeMain {
    public static void main(String[] args){
        ClassLoader cl = DayThreeMain.class.getClassLoader();
        URL pathURL = cl.getResource("dayThree/real.txt");
        File ff = new File(pathURL.getPath());
        ClaimManager clm = new ClaimManager(ff);
        System.out.println(clm.getOccupiedFields());

        clm.getClaimList().forEach(claim -> {
            if(clm.getNonOverlappingId(claim) != -1){
                System.out.println(claim.id);
            }
        });
    }
}
