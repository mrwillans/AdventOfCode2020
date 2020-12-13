package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class BoatCoords<Integer> {
    int direction;
    int x;
    int y;

    public BoatCoords(int direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
class WaypointCoords<Integer> {
    int direction;
    int x;
    int y;

    public WaypointCoords(int direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
public class Day12 {

    BoatCoords<Integer> getCoords(ArrayList<String> instructions) {

        BoatCoords boatCoords = new BoatCoords(90, 0, 0);
        for (String instr : instructions) {
            applyInstructions(instr, boatCoords);
        }
        return boatCoords;
    }

    public BoatCoords applyInstructions(String instr, BoatCoords boatCoords) {
        String direction = Character.toString(instr.charAt(0));
        switch (direction) {
            case "N":
                boatCoords.setY(boatCoords.y + Integer.parseInt(instr.replace("N", "")));
                break;
            case "S":
                boatCoords.setY(boatCoords.y - Integer.parseInt(instr.replace("S", "")));
                break;
            case "E":
                boatCoords.setX(boatCoords.x + Integer.parseInt(instr.replace("E", "")));
                break;
            case "W":
                boatCoords.setX(boatCoords.x - Integer.parseInt(instr.replace("W", "")));
                break;
            case "L":
                boatCoords.setDirection(boatCoords.getDirection() - Integer.parseInt(instr.replace("L", "")));
                if(boatCoords.direction >= 360){
                    boatCoords.setDirection(boatCoords.direction - 360);
                }
                if(boatCoords.direction < 0){
                    boatCoords.setDirection(boatCoords.direction + 360);
                }
                break;
            case "R":
                boatCoords.setDirection(boatCoords.getDirection() + Integer.parseInt(instr.replace("R", "")));
                if(boatCoords.direction >= 360){
                    boatCoords.setDirection(boatCoords.direction - 360);
                }
                if(boatCoords.direction < 0){
                    boatCoords.setDirection(boatCoords.direction + 360);
                }
                break;
            case "F":
                if (boatCoords.direction == 0) {
                    boatCoords.setY(boatCoords.y + Integer.parseInt(instr.replace("F", "")));
                }
                if (boatCoords.direction == 90) {
                    boatCoords.setX(boatCoords.x + Integer.parseInt(instr.replace("F", "")));
                }
                if (boatCoords.direction == 180) {
                    boatCoords.setY(boatCoords.y - Integer.parseInt(instr.replace("F", "")));
                }
                if (boatCoords.direction == 270) {
                    boatCoords.setX(boatCoords.x - Integer.parseInt(instr.replace("F", "")));
                }
                break;

        }
        return boatCoords;
    }

    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day12/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                instructions.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Day12 day12 = new Day12();
        BoatCoords boatCoords = day12.getCoords(instructions);
        System.out.println(Math.abs(boatCoords.x) + Math.abs(boatCoords.y));
    }
}
