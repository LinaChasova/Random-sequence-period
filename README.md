# Random-sequence-period
Data structure: MAP

You are given an implementation of not that good pseudorandom number generator :). 

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

When we explore such generators, it is very important to understand the length of the period: distance between first 2 repeating elements. E.g. if your sequence is A B C D E F G D E F G D E F G this will be distance between 2 occurrences of D, namely, 4. 

Your task is to estimate period length for given generator with different seed values. To complete this task you will need to implement Map ADT using AVL- or RB-tree. Read seed value from input.txt and write period length into output.txt. In your implementation you are required to implement insertion and search (removal is optional). 
