package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day5 {

    int getSeatID() {
        try {
            String line;
            int highestID = 0;
            ArrayList<String> rows = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/Day5/input.txt"));
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
            for (String pattern : rows) {
                String rowPattern = pattern.substring(0, 7);
                String columnPattern = pattern.substring(7, 10);

                ArrayList<Integer> seatRow = new ArrayList<>();
                for (int i = 0; i < 128; i++) {
                    seatRow.add(i);
                }
                for (int i = 0; i < rowPattern.length(); i++) {
                    char c = rowPattern.charAt(i);
                    if (Character.toString(c).equals("F")) {
                        seatRow.subList(((seatRow.size() / 2)), (seatRow.size() )).clear();
                    }
                    if (Character.toString(c).equals("B")) {
                        seatRow.subList(0, ((seatRow.size() / 2) )).clear();
                    }
                }

                ArrayList<Integer> seatColumn = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    seatColumn.add(i);
                }
                for (int i = 0; i < columnPattern.length(); i++) {
                    char c = columnPattern.charAt(i);
                    if (Character.toString(c).equals("L")) {
                        seatColumn.subList(((seatColumn.size() / 2)), (seatColumn.size() )).clear();
                    }
                    if (Character.toString(c).equals("R")) {
                        seatColumn.subList(0, (seatColumn.size() / 2)).clear();
                    }
                }
                int iD = 0;
                if (seatColumn.size() == 1 && seatColumn.size() == 1) {
                     iD = (seatRow.get(0) * 8) + seatColumn.get(0);
                }
                if(iD > highestID){
                    highestID = iD;
                }
            }
            return highestID;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        System.out.println(day5.getSeatID());
    }

}
