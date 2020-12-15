package Day15;

import java.util.*;

public class Day15 {

    private void play(int prevNumber, HashMap<Integer, Integer> map, int j) {
        if (j == 2021) {
            return;
        }
        if (Collections.frequency(new ArrayList<Integer>(map.values()), prevNumber) > 1) {
            int diff = j - 1 - Collections.max(getKeyByValue(map, prevNumber));
            map.put(j, diff);
            play(diff, map, j + 1);
        } else {
            map.put(j, 0);
            play(0, map, j + 1);
        }
    }

    private static Set<Integer> getKeyByValue(HashMap<Integer, Integer> map, int value) {
        Set<Integer> keys = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        keys.remove(Collections.max(keys));
        return keys;
    }

    public static void main(String[] args) {

        int[] start = new int[]{0, 8, 15, 2, 12, 1, 4}; //4};
        //int[] start = new int[]{0, 3, 6};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < start.length; i++) {
            map.put(i + 1, start[i]);
        }

        Day15 day15 = new Day15();
        day15.play(4, map, start.length + 1);
        System.out.println(map.get(2020));
    }
}
