package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Day1 {

    private int[] findInts(int target) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day1/input.txt"));
            String line;
            int i = 0;
            int[] array= new int[10000];
            HashMap<Integer, Integer> map = new HashMap<>();

            while ((line = br.readLine()) != null) {
                array[i] = Integer.parseInt(line);
                map.put(Integer.parseInt(line), i);
                i++;
            }
            for (int value : array) {
                int complement = target - value;
                if (map.containsKey(complement)) {
                    return new int[]{complement, value};
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        int[] pair = day1.findInts(2020);
        int soln = pair[0] * pair[1];
        System.out.println(soln);

    }
}
