package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {

    public Long[] findInts(Long target, int numOfSolns, ArrayList<Long> array) {
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            Long num = array.get(i);
            if (numOfSolns == 2) {
                Long complement = target - num;
                if (map.containsKey(complement)) {
                    return new Long[]{complement, num};
                }
            } else if (numOfSolns == 3) {
                Long secondTarget = target - num;
                for (Long value2 : array) {
                    Long secondComplement = secondTarget - value2;
                    if (map.containsKey(value2) && map.containsKey(secondComplement)) {
                        return new Long[]{value2, secondComplement, num};
                    }
                }

            }
            map.put(num, i);
        }
        return new Long[]{-1L, -1L};
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day1/input.txt"));
            String line;
            ArrayList<Long> array = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                array.add(Long.parseLong(line));
            }
            Day1 day1 = new Day1();
            Long[] pair = day1.findInts(2020L, 2, array);
            System.out.println(pair[0] * pair[1]);
            Long[] trio = day1.findInts(2020L, 3, array);
            Long soln = trio[0] * trio[1] * trio[2];
            System.out.println(soln);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
