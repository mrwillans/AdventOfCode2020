package Day13;

import Day12.Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day13 {

    int getTime(ArrayList<String> times) {
        int departure = Integer.parseInt(times.get(0));
        int lowestRemainder = 1000000000;
        String[] busses = times.get(1).replace("x,", "").split(",");
        HashMap<Integer, Integer> busAndRemainder = new HashMap<>();

        for (String bus : busses) {
            int remainder = Integer.parseInt(bus) - (departure % Integer.parseInt(bus));
            if (remainder < lowestRemainder) {
                lowestRemainder = remainder;
            }
            busAndRemainder.put(remainder, Integer.parseInt(bus));
        }
        return busAndRemainder.get(lowestRemainder) * lowestRemainder;
    }

    Long getTimePart2(String busses) {
        LinkedHashMap<Integer, Integer> busAndRemainder = new LinkedHashMap<>();
        int i = 0;
        for (String bus : busses.split(",")) {
            if (!bus.equals("x")) {
                busAndRemainder.put(Integer.parseInt(bus), i);
            }
            i++;
        }
     
        doTheStuff(busAndRemainder)
        return -1L;
    }

    int doTheStuff(int bus1, int bus2, int t0, int interval) {
        while (true) {
            int time1 = t0;
            int time2 = time1 + bus2;

            if (time1 - time2 == interval) {
                return time1;
            }
            time1 += bus1;
            time2 += bus2;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> timetable = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day13/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                timetable.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Day13 day13 = new Day13();
        System.out.println(day13.getTime(timetable));
        System.out.println(day13.getTimePart2(timetable.get(1)));
    }

}
