package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day11 {

    private int getOccupado(String[][] seats) {
        String[][] seatsCopy = Arrays.stream(seats).map(String[]::clone).toArray(String[][]::new);
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
            seats = Arrays.stream(seatsCopy).map(String[]::clone).toArray(String[][]::new);
        }


        for (String[] strings : seatsCopy) {
            for (String seat : strings) {
                if (seat.equals("#")) {
                    countOccupied++;
                }
            }
        }
        return countOccupied;
    }

    private int getOccupadoPart2(String[][] seats) {
        String[][] seatsCopy = Arrays.stream(seats).map(String[]::clone).toArray(String[][]::new);
        boolean stop = true;
        int countOccupied = 0;

        while (stop) {
            stop = false;
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    String seat = seats[i][j];
                    if (seat.equals("L")) {
                        int count = checkOccupiedPart2(seats, i, j);
                        if (count == 0) {
                            seatsCopy[i][j] = "#";
                            stop = true;
                        }

                    } else if (seat.equals("#")) {
                        int count = checkOccupiedPart2(seats, i, j);
                        if (count > 4) {
                            seatsCopy[i][j] = "L";
                            stop = true;
                        }
                    }
                }
            }
            seats = Arrays.stream(seatsCopy).map(String[]::clone).toArray(String[][]::new);
        }


        for (String[] strings : seatsCopy) {
            for (String seat : strings) {
                if (seat.equals("#")) {
                    countOccupied++;
                }
            }
        }
        return countOccupied;
    }

    private int checkOccupiedPart2(String[][] seats, int i, int j) {
        int count = 0;

        if (i > 0) {
            for (int x = i - 1; x >= 0; x--) {
                if (seats[x][j].equals("#")) {
                    count++;
                    break;
                } else if (seats[x][j].equals("L")) {
                    break;
                }
            }
        }
        if (j > 0) {
            for (int x = j - 1; x >= 0; x--) {
                if (seats[i][x].equals("#")) {
                    count++;
                    break;
                } else if (seats[i][x].equals("L")) {
                    break;
                }
            }
        }
        if (i < seats.length) {
            for (int x = i + 1; x < seats.length; x++) {
                if (seats[x][j].equals("#")) {
                    count++;
                    break;
                } else if (seats[x][j].equals("L")) {
                    break;
                }
            }
        }
        if (j < seats[i].length) {
            for (int x = j + 1; x < seats[i].length; x++) {
                if (seats[i][x].equals("#")) {
                    count++;
                    break;
                } else if (seats[i][x].equals("L")) {
                    break;
                }
            }
        }
        int[] steps = new int[]{1, 1, -1, -1, 1, -1, -1, 1};
        for (int s = 1; s < steps.length; s += 2) {
            int ystep = steps[s];
            int xstep = steps[s - 1];
            int x = i;
            int y = j;

            boolean flag = true;
            while (flag) {
                x += xstep;
                y += ystep;
                if (x >= 0 && y >= 0 && x < seats.length && y < seats[i].length) {
                    if (seats[x][y].equals("#")) {
                        count++;
                        flag = false;
                    } else if (seats[x][y].equals("L")) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        }
        return count;
    }

    private int checkOccupied(String[][] seats, int i, int j) {
        int count = 0;
        for (int dx = (i > 0 ? -1 : 0); dx < (i < seats.length - 1 ? 2 : 1); dx++) {
            for (int dy = (j > 0 ? -1 : 0); dy < (j < seats[i].length - 1 ? 2 : 1); dy++) {
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
        System.out.println(day11.getOccupadoPart2(seats));
    }
}
