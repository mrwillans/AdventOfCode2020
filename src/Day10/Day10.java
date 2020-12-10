package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day10 {

    private ArrayList<Integer> getDiffs(ArrayList<Integer> adapters) {
        ArrayList<Integer> diffs = new ArrayList<>();
        diffs.add(adapters.get(0));
        for (int i = 1; i < adapters.size(); i++) {
            diffs.add(adapters.get(i) - adapters.get(i - 1));
        }
        return diffs;
    }

    private Long getCombinations(ArrayList<Integer> adapters) {
        adapters.add(0, 0);
        adapters.add(adapters.size() - 1, (adapters.get(adapters.size() - 1) + 3));

        HashMap<Integer, Long> posPath = new HashMap<>();
        posPath.put(0, 1L);

        for (int i = 1; i < adapters.size(); i++) {
            posPath.put(adapters.get(i), 0L);
        }

        for (int val : adapters) {
            if (posPath.containsKey(val + 1)) {
                posPath.put(val + 1, posPath.get(val + 1) + posPath.get(val));
            }
            if (posPath.containsKey(val + 2)) {
                posPath.put(val + 2, posPath.get(val + 2) + posPath.get(val));
            }
            if (posPath.containsKey(val + 3)) {
                posPath.put(val + 3, posPath.get(val + 3) + posPath.get(val));
            }
        }
        return posPath.get(adapters.get(adapters.size()-1));
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
            System.out.println("soln = " + (ones * (threes + 1)));

            System.out.println(day10.getCombinations(adapters));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
