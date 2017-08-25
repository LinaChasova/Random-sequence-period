import java.io.*;
import java.util.Scanner;

/**
 * Created by AlinaCh on 24.03.2017.
 */
public class Main {

    public static MyRandom rand;

    /**
     * reads seed value from input file
     * @return seed value
     * @throws FileNotFoundException
     */
    public static int read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        return sc.nextInt();
    }

    /**
     * writes period to the output file
     * @param s
     */
    public static void write(String s) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "ascii"))) { writer.write(s); }
        catch (IOException ex) { }
    }

    /**
     * takes new generated number, if there's no such number in the map
     * inserts it, if there is counts the period
     * @return the period of the fisrt two equal numbers
     */
    public static Integer findPeriod() {
        MyAVLMap<Double, Integer> mymap = new MyAVLMap<>();
        int i = 0;
        Double d = rand.nextDouble();
        while (mymap.search(d) == null) {
            mymap.add(d, i);
            d = rand.nextDouble();
            i++;
        }
        return i - mymap.search(d);
    }

    public static void main(String[] args) throws FileNotFoundException {
        rand = new MyRandom(read());
        write(findPeriod().toString());
    }
}
