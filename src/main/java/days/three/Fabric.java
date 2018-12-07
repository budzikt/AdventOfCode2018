package days.three;

import days.three.claim.Claim;

import java.time.Year;
import java.util.Arrays;

public class Fabric {
    private final long X_MAX = 1000;
    private final long Y_MAX = 1000;

    public int[][] counts;

    public Fabric() {
    counts = new int[(int)X_MAX][(int)Y_MAX];
    }

    public void addClaimToFabric(Claim cl){
        if(cl.endX >= X_MAX || cl.endY >= Y_MAX){
            System.out.println("Horrible!");
        } else {
            for (long x = cl.startX; x <= cl.endX; x++) {
                for (long y = cl.startY; y <= cl.endY; y++) {
                    counts[(int)x][(int)y]++;
                }
            }
        }
    }

    public int getOverlapped() {
        int occupied = 0;
        for(int i = 0; i < X_MAX; i++){
            occupied += Arrays.stream(counts[i]).filter(element -> element > 1).toArray().length;
        }
        return occupied;
    }

    public int[][] getClaimOccupation(Claim cl) {
        int[][] r = new int[(int)cl.dimX][(int)cl.dimY];
        int rIdxX = 0;
        int rIdxY = 0;

        for(long x = cl.startX; x <= cl.endX; x++) {
            rIdxY = 0;
            for(long y = cl.startY; y <= cl.endY; y++) {
                int val = counts[(int)x][(int)y];
                r[rIdxX][rIdxY] = val;
                rIdxY++;
            }
            rIdxX++;
        }
        return r;
    }



}
