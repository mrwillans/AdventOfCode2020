package Day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day17 {

    public void part1(ArrayList<String> input) {
        char[][][] grid;
        grid = new char[input.size() + 20][input.size() + 20][input.size() + 20];
        for (int z = 0; z < grid.length; z++) {
            for (int y = 0; y < grid[0].length; y++) {
                for (int x = 0; x < grid[0][0].length; x++) {
                    grid[z][y][x] = '.';
                }
            }
        }

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                char c = input.get(y).charAt(x);
                grid[grid.length / 2 - input.size() / 2][grid.length / 2 - input.size() / 2 + y][grid.length / 2 + x] = c;
            }
        }

        for (int cycle = 1; cycle <= 6; cycle++) {
            char[][][] copy = new char[grid.length][grid.length][grid.length];
            for (int z = 0; z < grid.length; z++) {
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid.length; x++) {
                        int activeNeighbors = checkOccupied(grid, z, y, x);
                        if (grid[z][y][x] == '#') {
                            if (activeNeighbors == 2 || activeNeighbors == 3) {
                                copy[z][y][x] = '#';
                            } else {
                                copy[z][y][x] = '.';
                            }
                        } else if (grid[z][y][x] == '.') {
                            if (activeNeighbors == 3) {
                                copy[z][y][x] = '#';
                            } else {
                                copy[z][y][x] = '.';
                            }
                        }
                    }
                }
            }
            grid = copy;
        }

        int active = 0;
        for (int z = 0; z < grid.length; z++) {
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid.length; x++) {
                    if (grid[z][y][x] == '#') {
                        active++;
                    }
                }
            }
        }

        System.out.println("Cubes: " + active);

    }

    private static int checkOccupied(char[][][] seats, int i, int j, int k) {
        int count = 0;
        for (int dx = (i > 0 ? -1 : 0); dx < (i < seats.length - 1 ? 2 : 1); dx++) {
            for (int dy = (j > 0 ? -1 : 0); dy < (j < seats.length - 1 ? 2 : 1); dy++) {
                for (int dz = (k > 0 ? -1 : 0); dz < (k < seats.length - 1 ? 2 : 1); dz++) {
                    if (dx != 0 || dy != 0 || dz != 0) {
                        if (seats[i + dx][j + dy][k + dz] == '#') {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day17/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        Day17 day17 = new Day17();
        day17.part1(input);
    }
}
