package Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Day16 {

    private HashMap<String, ArrayList<Integer>> getFields(String lines) {
        HashMap<String, ArrayList<Integer>> fields = new HashMap<>();
        for (String line : lines.split("\n")) {
            String[] lineSplit = line.split(":");
            String[] ranges = lineSplit[1].split("or");
            ArrayList<Integer> nums = new ArrayList<>();
            for (String num : ranges) {
                for (String i : num.strip().split("-")) {
                    nums.add(Integer.parseInt(i));
                }
            }
            fields.put(lineSplit[0], nums);
        }
        return fields;
    }

    int part1(TreeSet allowedVals, String vals) {
        int sum = 0;
        for (String val : vals.split("\n")) {
            for (String num : val.split(",")) {
                if (!allowedVals.contains(Integer.parseInt(num))) {
                    sum += Integer.parseInt(num);
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        String allLines = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day16/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                allLines += line + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Day16 day16 = new Day16();
        HashMap<String, ArrayList<Integer>> fields = day16.getFields(allLines.split("\n\n")[0]);
        TreeSet<Integer> allowedVals = new TreeSet<>();
        for (ArrayList<Integer> vals : fields.values()) {
            for (int i = 0; i < vals.size(); i += 2) {
                for (int j : IntStream.rangeClosed(vals.get(i), vals.get(i + 1)).toArray()) {
                    allowedVals.add(j);
                }

            }
        }
        System.out.println(day16.part1(allowedVals, allLines.split("\n\n")[2].replace("nearby tickets:\n", "")));
    }
}
