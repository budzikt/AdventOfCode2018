package days.three.claim;

import days.three.Fabric;
import days.two.label.Label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaimManager {

    private ArrayList<Claim> claimList;
    private Fabric fabric;

    public ClaimManager() {
        claimList = new ArrayList<>();
        fabric = new Fabric();
    }

    public ClaimManager(File file) {
        this();
        try {
            BufferedReader reader = new BufferedReader( new FileReader(file));
            String line;
            Pattern numPattern = Pattern.compile("^#(\\d{1,})\\s*@\\s*(\\d{1,}),(\\d{1,}):\\s*(\\d{1,})x(\\d{1,})$");

            while((line = reader.readLine()) != null) {
                System.out.println(line);
                Matcher m = numPattern.matcher(line);
                m.matches();
                MatchResult mr = m.toMatchResult();
                long id = Long.parseLong(mr.group(1));
                long x = Long.parseLong(mr.group(2));
                long y = Long.parseLong(mr.group(3));
                long dimX = Long.parseLong(mr.group(4));
                long dimY = Long.parseLong(mr.group(5));
                claimList.add(new Claim(id, x, y, dimX, dimY));
            }
            claimList.forEach(claim -> fabric.addClaimToFabric(claim));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOccupiedFields(){
        return fabric.getOverlapped();
    }

    public int getNonOverlappingId(Claim cl) {
        int [][] area = fabric.getClaimOccupation(cl);

        long sum = 0;
        for(long x = 0; x < cl.dimX ; x++) {
            for(long y = 0; y < cl.dimY; y++) {
                sum+=area[(int)x][(int)y];
            }
        }

        long claimArea = cl.dimX * cl.dimY;
        if(sum == claimArea){
            return (int)cl.id;
        } else {
            return -1;
        }
    }

    public ArrayList<Claim> getClaimList() {
        return claimList;
    }
}
