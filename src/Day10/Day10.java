package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    ArrayList<Integer> getDiffs(ArrayList<Integer> adapters) {
        ArrayList<Integer> diffs = new ArrayList<>();

        diffs.add(adapters.get(0));
        for (int i = 1; i < adapters.size(); i++) {
            diffs.add(adapters.get(i) - adapters.get(i - 1));
        }

        return diffs;
    }


    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day10/input.txt"));
            String line;
            ArrayList<Integer> adapters = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                adapters.add(Integer.parseInt(line));
            }
            Collections.sort(adapters);

            Day10 day10 = new Day10();
            ArrayList<Integer> diffs = day10.getDiffs(adapters);

            int ones = Collections.frequency(diffs, 1);
            int threes = Collections.frequency(diffs, 3);
            System.out.println("soln = " + (ones * (threes+1)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
