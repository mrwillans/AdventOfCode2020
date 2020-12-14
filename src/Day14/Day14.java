package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Day14 {

    Long getSum(String[] allLines) {
        HashMap<Integer, Long> memory = new HashMap<>();
        for (int i = 1; i < allLines.length; i++) {
            String[] parts = allLines[i].split("\n");
            String bitmap = parts[0].strip();
            for (int j = 1; j < parts.length; j++) {
                String[] mem = parts[j].split("=");
                char[] binary = String.format("%36s", Integer.toBinaryString(Integer.parseInt(mem[1].strip()))).replace(' ', '0').toCharArray();
                for (int k = binary.length - 1; k >= 0; k--) {
                    if (!Character.toString(bitmap.charAt(k)).equals("X")) {
                        if (binary[k] == '0' && bitmap.charAt(k) == '1') {
                            binary[k] = '1';
                        } else if (binary[k] == '1' && bitmap.charAt(k) == '0') {
                            binary[k] = '0';
                        }

                    }
                }
                Long result = Long.parseLong(String.valueOf(binary), 2);
                String loc = mem[0].replace("mem[", "").replace("]", "").strip();
                memory.put(Integer.parseInt(loc), result);
            }
        }
        Long sum = 0L;
        for (int key : memory.keySet()) {
            sum += memory.get(key);
        }

        return sum;
    }

    public static void main(String[] args) {
        String allLines = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day14/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                allLines += line + "\n";

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Day14 day14 = new Day14();
        System.out.println(day14.getSum(allLines.split("mask = ")));
    }
}
