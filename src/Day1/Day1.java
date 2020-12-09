package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {

    private int[] findInts(int target, int numOfSolns, ArrayList<Integer> array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            int num = array.get(i);
            if (numOfSolns == 2) {
                int complement = target - num;
                if (map.containsKey(complement)) {
                    return new int[]{complement, num};
                }
            } else if (numOfSolns == 3) {
                int secondTarget = target - num;
                for (int value2 : array) {
                    int secondComplement = secondTarget - value2;
                    if (map.containsKey(value2) && map.containsKey(secondComplement)) {
                        return new int[]{value2, secondComplement, num};
                    }
                }

            }
            map.put(num, i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day1/input.txt"));
            String line;
            ArrayList<Integer> array = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                array.add(Integer.parseInt(line));
            }
            Day1 day1 = new Day1();
            int[] pair = day1.findInts(2020, 2, array);
            System.out.println(pair[0] * pair[1]);
            int[] trio = day1.findInts(2020, 3, array);
            int soln = trio[0] * trio[1] * trio[2];
            System.out.println(soln);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
