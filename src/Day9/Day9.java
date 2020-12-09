package Day9;

import Day1.Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day9 {

    private Long getFirstNum(ArrayList<Long> nums, Day1 day1) {
        for (int i = 0, j = 24; j < nums.size(); i++, j++) {
            Long target = nums.get(j + 1);

            nums.subList(i, j);

            Long[] answer = day1.findInts(target, 2, nums);

            if (answer[0] == -1 && answer[1] == -1) {
                return target;
            }
        }
        return -1L;

    }

    private ArrayList<Long> getSummingNums(ArrayList<Long> array, Long target) {
        for (int i = 0; i < array.size(); i++) {
            Long sum = 0L;
            ArrayList<Long> solns = new ArrayList<>();
            for (int j = i; j < array.size(); j++) {
                sum += array.get(j);
                solns.add(array.get(j));
                if (sum.equals(target)) {
                    return solns;
                }
                if (sum > target) {
                    break;
                }
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day9/input.txt"));
            String line;
            ArrayList<Long> array = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                array.add(Long.parseLong(line));
            }

            Day1 day1 = new Day1();
            Day9 day9 = new Day9();

            Long solnP1 = day9.getFirstNum(array, day1);
            System.out.println(solnP1);

            ArrayList<Long> solns = day9.getSummingNums(array, solnP1);
            Collections.sort(solns);
            Long sum = solns.get(0) + solns.get(solns.size() - 1);
            System.out.println(sum);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
