package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Day1 {

    private int[] findInts(int target, int numOfSolns) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day1/input.txt"));
            String line;
            int i = 0;
            int[] array = new int[10000];
            HashMap<Integer, Integer> map = new HashMap<>();

            while ((line = br.readLine()) != null) {
                array[i] = Integer.parseInt(line);
                map.put(Integer.parseInt(line), i);
                i++;
            }

            if (numOfSolns == 2) {
                for (int value : array) {
                    int complement = target - value;
                    if (map.containsKey(complement)) {
                        return new int[]{complement, value};
                    }
                }
            } else if (numOfSolns == 3) {
                for (int value : array) {
                    int secondTarget = target - value;
                    for (int value2 : array) {
                        int secondComplement = secondTarget - value2;
                        if (map.containsKey(value2) && map.containsKey(secondComplement)) {
                            return new int[]{value2, secondComplement, value};
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        int[] pair = day1.findInts(2020, 2);
        System.out.println(pair[0] * pair[1]);
        int[] trio = day1.findInts(2020, 3);
        int soln = trio[0] * trio[1] * trio[2];
        System.out.println(soln);

    }
}
