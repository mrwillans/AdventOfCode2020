package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day8 {

    private int getAcc(String part) {
        try {
            String line;
            LinkedList<String> instructions = new LinkedList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/Day8/input.txt"));
            while ((line = br.readLine()) != null) {
                instructions.add(line);
            }

            int accumulator = 0;
            Set<Integer> usedInstructions = new HashSet<>();
            int jump = 0;

            if (part.equals("part1")) {
                for (int i = 0; i < instructions.size(); i += jump) {
                    String individualInstruction = instructions.get(i);
                    if (usedInstructions.contains(i)) {
                        return accumulator;
                    }
                    String[] split = individualInstruction.split(" ");
                    switch (split[0]) {
                        case "acc":
                            accumulator += Integer.parseInt(split[1]);
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
            }
            if (part.equals("part2")) {
                for (int i = 0; i < instructions.size(); i++) {
                    LinkedList<String> instructionsCopy = new LinkedList<>(instructions);

                    Set<Integer> usedInstructionsPart2 = new HashSet<>();
                    int accumalatorPart2 = 0;
                    if (instructionsCopy.get(i).contains("nop") || instructionsCopy.get(i).contains("jmp")) {
                        if (instructionsCopy.get(i).contains("nop")) {
                            instructionsCopy.set(i,(instructionsCopy.get(i).replace("nop", "jmp")));
                        } else if (instructionsCopy.get(i).contains("jmp")) {
                            instructionsCopy.set(i,(instructionsCopy.get(i).replace("jmp", "nop")));
                        }

                        for (int j = 0; j < instructionsCopy.size(); j += jump) {
                            String[] split2 = instructionsCopy.get(j).split(" ");

                            if (usedInstructionsPart2.contains(j)) {
                                accumalatorPart2 = 0;
                                break;
                            }
                            switch (split2[0]) {
                                case "acc":
                                    accumalatorPart2 += Integer.parseInt(split2[1]);
                                    usedInstructionsPart2.add(j);
                                    jump = 1;
                                    break;
                                case "jmp":
                                    jump = Integer.parseInt(split2[1]);
                                    usedInstructionsPart2.add(j);
                                    break;
                                case "nop":
                                    usedInstructionsPart2.add(j);
                                    jump = 1;
                            }
                        }
                    }
                    if (accumalatorPart2 != 0) {
                        return accumalatorPart2;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        System.out.println(day8.getAcc("part1"));
        System.out.println(day8.getAcc("part2"));
    }
}
