package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day8 {

    private int getAcc() {
        try {
            String line;
            LinkedList<String> instructions = new LinkedList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/Day8/input.txt"));
            while ((line = br.readLine()) != null) {
                instructions.add(line);
            }

            int accumulator =0;
            Set<Integer> usedInstructions = new HashSet<>();
            int jump=0;

            for(int i=0; i<instructions.size(); i+=jump){
                String individualInstruction = instructions.get(i);
                if(usedInstructions.contains(i)){
                    return accumulator;
                }
                String[] split = individualInstruction.split(" ");
                switch (split[0]){
                    case "acc":
                        accumulator+=Integer.parseInt(split[1]);
                        usedInstructions.add(i);
                        jump = 1;
                        break;
                    case "jmp":
                        jump = Integer.parseInt(split[1]);
                        usedInstructions.add(i);
                        break;
                    case "nop":
                        usedInstructions.add(i);
                        jump = 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        System.out.println(day8.getAcc());
    }
}
