package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day5 {

    ArrayList<Integer> getSeatID() {
        try {
            String line;
            int highestID = 0;
            ArrayList<Integer> calculatedSeatIds = new ArrayList<>();
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
                        seatRow.subList(((seatRow.size() / 2)), (seatRow.size())).clear();
                    }
                    if (Character.toString(c).equals("B")) {
                        seatRow.subList(0, ((seatRow.size() / 2))).clear();
                    }
                }

                ArrayList<Integer> seatColumn = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    seatColumn.add(i);
                }
                for (int i = 0; i < columnPattern.length(); i++) {
                    char c = columnPattern.charAt(i);
                    if (Character.toString(c).equals("L")) {
                        seatColumn.subList(((seatColumn.size() / 2)), (seatColumn.size())).clear();
                    }
                    if (Character.toString(c).equals("R")) {
                        seatColumn.subList(0, (seatColumn.size() / 2)).clear();
                    }
                }
                int iD = 0;
                if (seatColumn.size() == 1 && seatColumn.size() == 1) {
                    iD = (seatRow.get(0) * 8) + seatColumn.get(0);
                    calculatedSeatIds.add(iD);
                }
                if (iD > highestID) {
                    highestID = iD;
                }
            }
            //part 1
            //return highestID;

            //part 2
            return calculatedSeatIds;
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    ArrayList<Integer> allSeatIDs() {
        ArrayList<Integer> allSeatIds = new ArrayList<>();
        ArrayList<Integer> seatRow = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            seatRow.add(i);
        }
        ArrayList<Integer> seatColumn = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            seatColumn.add(i);
        }
        for(int x: seatColumn){
            for(int y: seatRow){
                allSeatIds.add((y*8)+x);
            }
        }
        return allSeatIds;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        ArrayList<Integer> allIds = day5.allSeatIDs();
        Set<Integer> allIdset = new HashSet<>(allIds);
        ArrayList<Integer> allIdsCalc = day5.getSeatID();
        allIdset.removeAll(allIdsCalc);
        System.out.println(allIdset);
    }

}
