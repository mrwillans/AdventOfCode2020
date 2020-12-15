package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day14 {

    Long getSum(String[] allLines, boolean part1) {
        HashMap<Long, Long> memory = new HashMap<>();
        for (int i = 1; i < allLines.length; i++) {
            String[] parts = allLines[i].split("\n");
            String bitmap = parts[0].strip();
            if (part1) {
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
                    memory.put(Long.parseLong(loc), result);
                }
            } else {
                for (int j = 1; j < parts.length; j++) {
                    String[] mem = parts[j].split("=");
                    char[] binary = String.format("%36s", Integer.toBinaryString(Integer.parseInt(mem[0].replace("mem[", "").replace("]", "").strip()))).replace(' ', '0').toCharArray();
                    for (int k = binary.length - 1; k >= 0; k--) {
                        if (!Character.toString(bitmap.charAt(k)).equals("0")) {
                            if (bitmap.charAt(k) == '1') {
                                binary[k] = '1';
                            } else if (bitmap.charAt(k) == 'X') {
                                binary[k] = 'X';
                            }
                        }
                    }
                    ArrayList<String> addresses = new ArrayList<>();
                    generateAddresses(String.valueOf(binary), addresses);
                    for (String addr : addresses) {
                        memory.put(Long.parseLong(addr, 2), Long.parseLong(mem[1].strip()));
                    }
                }

            }
        }
        Long sum = 0L;
        for (Long val : memory.values()) {
            sum += val;
        }

        return sum;
    }

    private void generateAddresses(String binary, List<String> addresses) {
        if (!binary.contains("X")) {
            addresses.add(binary);
            return;
        }
        String one = binary, two = binary;
        one = one.replaceFirst("X", "1");
        two = two.replaceFirst("X", "0");
        generateAddresses(one, addresses);
        generateAddresses(two, addresses);
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
        System.out.println(day14.getSum(allLines.split("mask = "), true));
        System.out.println(day14.getSum(allLines.split("mask = "), false));
    }
}
