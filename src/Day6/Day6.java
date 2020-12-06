package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Day6 {

    int getQuestionsAnswered(String part) {
        try {
            String line;
            String allLines = "";
            BufferedReader br = new BufferedReader(new FileReader("src/Day6/input.txt"));
            while ((line = br.readLine()) != null) {
                allLines += line + "\n";
            }
            int count = 0;
            String[] listGroups = allLines.split("\\n\\n");
            if (part.equals("part1")) {
                for (String group : listGroups) {
                    Set<String> questionsAnswered = new HashSet<>();
                    for (String person : group.split("\n")) {
                        for (int i = 0; i < person.length(); i++) {
                            char c = person.charAt(i);
                            questionsAnswered.add(Character.toString(c));
                        }
                    }
                    count += questionsAnswered.size();
                }
            }
            if (part.equals("part2")) {
                for (String group : listGroups) {
                    Set<String> questionsAnswered = new HashSet<>();
                    ArrayList<String> allQuestionsAnswered = new ArrayList<>();
                    String[] eachPerson = group.split("\n");
                    for (String person : eachPerson) {
                        for (int i = 0; i < person.length(); i++) {
                            char c = person.charAt(i);
                            allQuestionsAnswered.add(Character.toString(c));
                            questionsAnswered.add(Character.toString(c));
                        }
                    }
                    for (String question : questionsAnswered) {
                        int freq = Collections.frequency(allQuestionsAnswered, question);
                        if (freq == eachPerson.length) {
                            count++;
                        }
                    }
                }
            }
            return count;

        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.getQuestionsAnswered("part1"));
        System.out.println(day6.getQuestionsAnswered("part2"));
    }
}
