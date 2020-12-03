package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day3 {

    private int getTrees(int xStep, int yStep) {
        try {
            String line;
            ArrayList<String> forest = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/Day3/input.txt"));
            while ((line = br.readLine()) != null) {
                forest.add(line);
            }

            int xPos = 0;
            int count = 0;
            for (int i = 0; i < forest.size(); i += yStep) {
                String row = forest.get(i);
                if (xPos > (row.length() - 1)) {
                    xPos = xPos - (row.length());
                }
                String positionChar = Character.toString(row.charAt(xPos));
                if (positionChar.equals("#")) {
                    count++;
                }
                xPos += xStep;

            }
            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();

        //part 1
        System.out.println(day3.getTrees(3, 1));

        //part 2
        double one = day3.getTrees(1, 1);
        double two = day3.getTrees(3, 1);
        double three = day3.getTrees(5, 1);
        double four = day3.getTrees(7, 1);
        double five = day3.getTrees(1, 2);
        double soln = one * two * three * four * five;
        System.out.printf("%f", soln);
    }
}
