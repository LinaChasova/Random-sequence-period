/**
 * Created by AlinaCh on 25.03.2017.
 */
public class MyRandom {

    private double now = 0;
    private final long seed;

    public MyRandom(long seed) {
        this.seed = seed;
    }

    public double nextDouble() {
        return now = Math.abs(
                (long)((long)1e11d * Math.sin(now * 10101 + seed)) / 1e11d
        );
    }
}
