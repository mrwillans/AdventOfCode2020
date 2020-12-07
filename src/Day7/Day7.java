package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    private void recursionCount(String bag, ArrayList<String> allbags, Set<String> uniqueBagsContainGold) {
        for (String row : allbags) {
            if (row.contains(bag) && !row.startsWith(bag)) {
                String[] bags = row.split(" ");
                String bagAndColour = bags[0] + " " + bags[1];
                uniqueBagsContainGold.add(bagAndColour);
                recursionCount(bagAndColour, allbags, uniqueBagsContainGold);
            }
        }
    }

    private int recursionCountBags(String bag, ArrayList<String> allbags, ArrayList<Integer> uniqueBagsAndCount) {
        int count = 0;
        for (String row : allbags) {
            if (row.startsWith(bag)) {
                Pattern pattern = Pattern.compile("\\d\\s\\w*\\s\\w*");
                Matcher matcher = pattern.matcher(row);
                while (matcher.find()) {
                    for (int i = 0; i <= matcher.groupCount(); i++) {
                        String[] bagAndNum = matcher.group(i).split(" ");
                        count += ((Integer.parseInt(bagAndNum[0])) * (recursionCountBags((bagAndNum[1] + " " + bagAndNum[2]), allbags, uniqueBagsAndCount) +1 ));
                    }
                }
                return count;
            }
        }
        return -1;
    }

    private void getBags() {
        try {
            String line;
            ArrayList<String> bags = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/Day7/input.txt"));
            while ((line = br.readLine()) != null) {
                bags.add(line);
            }
            Set<String> uniqueBagsContainGold = new HashSet<>();
            recursionCount("shiny gold", bags, uniqueBagsContainGold);
            System.out.println(uniqueBagsContainGold.size());

            ArrayList<Integer> uniqueBagsCount = new ArrayList<>();
            System.out.println(recursionCountBags("shiny gold", bags, uniqueBagsCount));

        } catch (
                Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        day7.getBags();

    }
}
