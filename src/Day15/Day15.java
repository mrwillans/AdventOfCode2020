package Day15;

import java.util.*;

public class Day15 {

    private void play(Long prevNumber, HashMap<Long, ArrayList<Long>> map, Long start) {
        for (Long j = start; j < 30000001; j++) {
            if (map.get(prevNumber).size() > 1) {
                List<Long> l = map.get(prevNumber);
                Long diff = l.get(l.size() - 1) - l.get(l.size() - 2);
                if (map.containsKey(diff)) {
                    map.get(diff).add(j);
                } else {
                    map.put(diff, new ArrayList<>());
                    map.get(diff).add(j);
                }
                prevNumber = diff;
            } else {
                map.get(0L).add(j);
                prevNumber = 0L;
            }
        }
        System.out.println(prevNumber);
    }


    public static void main(String[] args) {

        Long[] start = new Long[]{0L, 8L, 15L, 2L, 12L, 1L, 4L}; //4};

        HashMap<Long, ArrayList<Long>> map = new HashMap<>();

        for (int i = 0; i < start.length; i++) {
            map.put(start[i], new ArrayList<>());
            map.get(start[i]).add((long) i + 1);
        }

        Day15 day15 = new Day15();
        day15.play(4L, map, start.length + 1L);
    }
}
