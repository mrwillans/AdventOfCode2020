package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day11 {

    int getOccupado(String[][] seats) {
        String [][] seatsCopy = Arrays.stream(seats).map(String[]::clone).toArray(String[][]::new);
        boolean stop = true;
        int countOccupied = 0;

        while (stop) {
            stop = false;
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    String seat = seats[i][j];
                    if (seat.equals("L")) {
                        int count = checkOccupied(seats, i, j);
                        if (count == 0) {
                            seatsCopy[i][j] = "#";
                            stop = true;
                        }

                    } else if (seat.equals("#")) {
                        int count = checkOccupied(seats, i, j);
                        if (count > 3) {
                            seatsCopy[i][j] = "L";
                            stop = true;
                        }
                    }
                }
            }
            seats=Arrays.stream(seatsCopy).map(String[]::clone).toArray(String[][]::new);
        }


        for (int i = 0; i < seatsCopy.length; i++) {
            for (int j = 0; j < seatsCopy[i].length; j++) {
                String seat = seatsCopy[i][j];
                if (seat.equals("#")) {
                    countOccupied++;
                }
            }
        }
        return countOccupied;
    }

    int checkOccupied(String[][] seats, int i, int j) {
        int count = 0;
        for (int dx = (i > 0 ? -1 : 0); dx < (i < seats.length-1 ? 2 : 1); dx++) {
            for (int dy = (j > 0 ? -1 : 0); dy < (j < seats[i].length-1 ? 2: 1); dy++) {
                if (dx != 0 || dy != 0) {
                    if (seats[i + dx][j + dy].equals("#")) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String[][] seats = new String[91][90];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day11/input.txt"));
            String line;
            int lineI = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < line.length(); j++) {
                    char charac = line.charAt(j);
                    seats[lineI][j] = Character.toString(charac);
                }
                lineI++;
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        Day11 day11 = new Day11();
        System.out.println(day11.getOccupado(seats));
    }
}
