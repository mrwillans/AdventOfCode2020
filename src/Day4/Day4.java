package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Day4 {

    int getValidPassports(String part) {
        try {
            String line;
            String allLines = "";
            BufferedReader br = new BufferedReader(new FileReader("src/Day4/input.txt"));
            while ((line = br.readLine()) != null) {
                allLines += line + "\n";
            }
            int count = 0;
            String[] listPassports = allLines.split("\\n\\n");
            for (String passport : listPassports) {
                HashMap<String, String> individualPassport = new HashMap<>();
                for (String field : passport.replace("\n", " ").split("\\s")) {
                    String[] vals = field.split(":");
                    individualPassport.put(vals[0], vals[1]);
                }
                if (part == "part1") {
                    if (individualPassport.size() == 8) {
                        count++;
                    } else if (individualPassport.size() == 7 && !individualPassport.containsKey("cid")) {
                        count++;
                    }
                }
                if (part == "part2") {
                    if (individualPassport.size() == 8) {
                        if (validate(individualPassport)) {
                            count++;
                        }
                    } else if (individualPassport.size() == 7 && !individualPassport.containsKey("cid")) {
                        if (validate(individualPassport)) {
                            count++;
                        }
                    }
                }
            }
            return count;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    boolean validate(HashMap<String, String> map) {
        return validateByr(Integer.parseInt(map.get("byr"))) &&
                validateIyr(Integer.parseInt(map.get("iyr"))) &&
                validateEyr(Integer.parseInt(map.get("eyr"))) &&
                validateHgt(map.get("hgt")) &&
                validateHcl(map.get("hcl")) &&
                validateEcl(map.get("ecl")) &&
                validatePid(map.get("pid"));

    }

    boolean validateNum(int num, int lower, int upper) {
        return num >= lower && num <= upper;
    }

    boolean validateByr(int num) {
        return validateNum(num, 1920, 2002);
    }

    boolean validateIyr(int num) {
        return validateNum(num, 2010, 2020);
    }

    boolean validateEyr(int num) {
        return validateNum(num, 2020, 2030);
    }

    boolean validateHgt(String num) {
        if (num.matches("[0-9]{3}cm")) {
            return validateNum(Integer.parseInt(num.replace("cm", "")), 150, 193);
        }
        else if (num.matches("[0-9]{2}in")) {
            return validateNum(Integer.parseInt(num.replace("in", "")), 59, 76);
        }
        return false;
    }

    boolean validateHcl(String hcl) {
        return hcl.matches("#[0-9\\|a-f]{6}");
    }

    boolean validateEcl(String ecl) {
        return ecl.matches("amb|blu|brn|gry|grn|hzl|oth");
    }

    boolean validatePid(String pid) {
        return pid.matches("[0-9]{9}");
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        int count = day4.getValidPassports("part1");
        System.out.println(count);
        int part2 = day4.getValidPassports("part2");
        System.out.println(part2);
    }
}
