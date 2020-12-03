package Day3;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day3 {

    int getTrees() {
        try {
            String line;
            String[] forest = new String[323];
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader("src/Day3/input.txt"));
            while ((line = br.readLine()) != null) {
                forest[i] = line;
                i++;
            }

            int xPos = 0;
            int count = 0;
            for (String row : forest) {
                if (xPos > (row.length()-1)) {
                    xPos = xPos - (row.length());
                }
                String positionChar = Character.toString(row.charAt(xPos));
                if (positionChar.equals("#")) {
                    count++;
                }
                xPos += 3;
            }
            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println(day3.getTrees());
    }
}


