package days.three.claim;

public class Claim {
    public long id;

    public long startX;
    public long endX;

    public long startY;
    public long endY;

    public long dimX;
    public long dimY;

    public Claim(long id, long startX, long startY, long dimX, long dimY) {
        this.id = id;
        this.startX = startX ;
        this.startY = startY ;
        this.dimX = dimX;
        this.dimY = dimY;

        this.endX = this.startX + dimX -1;
        this.endY = this.startY + dimY -1;
    }
}
