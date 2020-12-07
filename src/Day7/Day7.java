package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day7 {

    private void recursionCount(String bag, ArrayList<String> allbags, Set<String> uniqueBagsContainGold) {
        for (String row : allbags) {
            if (row.contains(bag) && !row.startsWith(bag)) {
                String[] bags = row.split(" ");
                String bagAndColour =  bags[0] + " " + bags[1];
                uniqueBagsContainGold.add(bagAndColour);
                recursionCount(bagAndColour, allbags, uniqueBagsContainGold);
            }
        }
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
