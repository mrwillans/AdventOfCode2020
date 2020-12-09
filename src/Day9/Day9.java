package Day9;

import Day1.Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day9 {

    Long getFirstNum(ArrayList<Long> nums, Day1 day1) {
        for (int i = 0, j = 25; j < nums.size(); i++, j++) {
            Long target = nums.get(j + 1);

            nums.subList(i, j);

            Long[] answer = day1.findInts(target, 2, nums);

            if (answer[0] == -1 && answer[1] == -1) {
                return target;
            }
        }
        return -1L;

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

            System.out.println(day9.getFirstNum(array, day1));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
