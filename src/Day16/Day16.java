package Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Day16 {

    private HashMap<String, TreeSet<Integer>> getFields(String lines) {
        HashMap<String, TreeSet<Integer>> fields = new HashMap<>();
        for (String line : lines.split("\n")) {
            String[] lineSplit = line.split(":");
            String[] ranges = lineSplit[1].split("or");
            ArrayList<Integer> nums = new ArrayList<>();
            for (String num : ranges) {
                for (String i : num.strip().split("-")) {
                    nums.add(Integer.parseInt(i));
                }
            }
            TreeSet<Integer> allowedVals = new TreeSet<>();
            for (int i = 0; i < nums.size(); i += 2) {
                for (int j : IntStream.rangeClosed(nums.get(i), nums.get(i + 1)).toArray()) {
                    allowedVals.add(j);
                }
            }
            fields.put(lineSplit[0], allowedVals);
        }
        return fields;
    }

    private ArrayList<String> part1(TreeSet allowedVals, String vals) {
        int sum = 0;
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(vals.split("\n")));
        Set<Integer> linesToRemove = new LinkedHashSet<>();
        for (int i = 0; i < lines.size(); i++) {
            for (String num : lines.get(i).split(",")) {
                if (!allowedVals.contains(Integer.parseInt(num))) {
                    sum += Integer.parseInt(num);
                    linesToRemove.add(i);
                }
            }
        }
        ArrayList<Integer> linesToRemoveArray = new ArrayList<>(linesToRemove);
        for (int x = linesToRemoveArray.size() - 1; x >= 0; x--) {
            int index = linesToRemoveArray.get(x);
            lines.remove(index);
        }
        System.out.println("SUM: " + sum);
        return lines;
    }

    Long part2(ArrayList<String> validNearby, HashMap<String, TreeSet<Integer>> fields, String myTicket) {
        ArrayList<String> keys = new ArrayList<>(fields.keySet());

        HashMap<Integer, String> result = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            for (String key : keys) {
                ArrayList<Integer> valid = new ArrayList<>();
                for (String ticket : validNearby) {
                    int val = Integer.parseInt(ticket.split(",")[i]);
                    if (fields.get(key).contains(val)) {
                        valid.add(val);
                    }
                }
                if (valid.size() == validNearby.size()) {
                    if (!result.containsKey(i)) {
                        result.put(i, key);
                    } else {
                        result.put(i, result.get(i) + "," + key);
                    }
                }
            }
        }
        HashMap<String, Integer> finalResult = new HashMap<>();
        boolean flag = true;
        while (flag) {
            for (int column : result.keySet()) {
                if (!result.get(column).isBlank() && result.get(column).split(",").length == 1) {
                    String val = result.get(column);
                    finalResult.put(val, column);
                    result.replaceAll((c, v) -> result.get(c).replace(val + ",", "").replace("," + val, ""));
                    result.put(column, "");
                }
            }
            if (finalResult.size() == 20) {
                flag = false;
            }
        }

        ArrayList<String> newKeys = new ArrayList<>(fields.keySet());
        ArrayList<String> departureKeys = new ArrayList<>();
        for (String key : newKeys) {
            if (key.contains("departure")) {
                departureKeys.add(key);
            }
        }
        Long sum = 1L;
        for (String key : departureKeys) {
            int index = finalResult.get(key);
            sum *= Long.parseLong(myTicket.split(",")[index]);
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
        HashMap<String, TreeSet<Integer>> fields = day16.getFields(allLines.split("\n\n")[0]);
        String nearbyTickets = allLines.split("\n\n")[2].replace("nearby tickets:\n", "");

        TreeSet<Integer> allowedVals = new TreeSet<>();
        for (TreeSet<Integer> vals : fields.values()) {
            allowedVals.addAll(vals);
        }
        ArrayList<String> validNeaby = day16.part1(allowedVals, nearbyTickets);

        System.out.println(day16.part2(validNeaby, fields, allLines.split("\n\n")[1].replace("your ticket:\n", "")));
    }
}
