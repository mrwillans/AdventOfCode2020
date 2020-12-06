package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Day6 {

    int getQuestionsAnswered() {
        try {
            String line;
            String allLines = "";
            BufferedReader br = new BufferedReader(new FileReader("src/Day6/input.txt"));
            while ((line = br.readLine()) != null) {
                allLines += line + "\n";
            }
            int count = 0;
            String[] listGroups = allLines.split("\\n\\n");
            for (String group: listGroups){
                Set<String> questionsAnswered = new HashSet<>();
                for(String person: group.split("\n")){
                    for(int i=0; i<person.length(); i++) {
                        char c = person.charAt(i);
                        questionsAnswered.add(Character.toString(c));
                    }
                }
                count += questionsAnswered.size();
            }
            return count;

        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.getQuestionsAnswered());
    }
}
