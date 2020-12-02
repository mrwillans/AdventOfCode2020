package Day2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day2 {


    private int numOfValidPasswords(String part) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day2/input.txt"));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                int charCount = 0;
                String[] wholeLine = line.split(":");
                String password = wholeLine[1].strip();
                String[] regexVars = wholeLine[0].split("\\s");
                String[] range = regexVars[0].split("-");
                String letter = regexVars[1];

                if (part.equals("part1")){
                    int lowerbound = Integer.parseInt(range[0]);
                    int upperbound = Integer.parseInt(range[1]);

                    for (int i = 0; i < password.length(); i++) {
                        if (letter.equals(Character.toString(password.charAt(i)))) {
                            charCount++;
                        }
                    }
                    if (charCount >= lowerbound && charCount <= upperbound) {
                        count++;
                    }
                }
                if (part.equals("part2")){
                    int pos1 = Integer.parseInt(range[0]);
                    int pos2 = Integer.parseInt(range[1]);

                    String pos1Let = Character.toString(password.charAt(pos1 - 1));
                    String pos2Let = Character.toString(password.charAt(pos2 - 1));

                    if (pos1Let.equals(letter)) {
                        charCount++;
                    }
                    if (pos2Let.equals(letter))
                        charCount++;

                    if (charCount == 1) {
                        count++;
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
        Day2 day2 = new Day2();
        System.out.println(day2.numOfValidPasswords("part1"));
        System.out.println(day2.numOfValidPasswords("part2"));
    }
}
