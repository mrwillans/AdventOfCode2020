package Day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Day22 {

    private LinkedList<Integer> splitInput(String input) {
        LinkedList<Integer> queue = new LinkedList<>();
        String[] nums = input.split("\n");
        for (int i = 1; i < nums.length; i++) {
            queue.add(Integer.parseInt(nums[i]));
        }
        return queue;
    }

    private LinkedList<Integer> playGame(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        while (!player1.isEmpty() && !player2.isEmpty()) {
            int p1 = player1.remove();
            int p2 = player2.remove();

            if (p1 > p2) {
                player1.add(p1);
                player1.add(p2);
            } else if (p2 > p1) {
                player2.add(p2);
                player2.add(p1);
            }
        }

        if (!player1.isEmpty()) {
            return player1;
        } else {
            return player2;
        }
    }

    private String playGameRecursive(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        HashMap<String, ArrayList<LinkedList<Integer>>> playedHands = new HashMap<>();
        playedHands.put("player1", new ArrayList<>());
        playedHands.put("player2", new ArrayList<>());

        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (playedHands.get("player1").contains(player1) && playedHands.get("player2").contains(player2)) {
                return "player1";
            }

            playedHands.get("player1").add(new LinkedList<>(player1));
            playedHands.get("player2").add(new LinkedList<>(player2));

            int p1 = player1.remove();
            int p2 = player2.remove();

            if (player1.size() > p1 - 1 && player2.size() > p2 - 1) {
                String winner = playGameRecursive(new LinkedList<>(player1.subList(0, p1)), new LinkedList<>(player2.subList(0, p2)));
                if (winner.equals("player1")) {
                    player1.add(p1);
                    player1.add(p2);
                } else {
                    player2.add(p2);
                    player2.add(p1);
                }
                continue;
            }

            if (p1 > p2) {
                player1.add(p1);
                player1.add(p2);
            } else if (p2 > p1) {
                player2.add(p2);
                player2.add(p1);
            }
        }
        Long sum = 0L;
        if (!player1.isEmpty()) {
            for (int i = 1; i <= player1.size(); i++) {
                sum += (player1.get(player1.size() - i) * i);
            }
            System.out.println(sum);
            return "player1";
        } else {
            for (int i = 1; i <= player2.size(); i++) {
                sum += (player2.get(player2.size() - i) * i);
            }
            System.out.println(sum);
            return "player2";
        }
    }

    public static void main(String[] args) {
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Day22/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                input += line + "\n";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        Day22 day22 = new Day22();
        LinkedList<Integer> player1 = day22.splitInput(input.split("\n\n")[0]);
        LinkedList<Integer> player2 = day22.splitInput(input.split("\n\n")[1]);
        LinkedList<Integer> player1Copy = new LinkedList<>(player1);
        LinkedList<Integer> player2Copy = new LinkedList<>(player2);


        LinkedList<Integer> winner = day22.playGame(player1, player2);

        int sum = 0;
        for (int i = 1; i <= winner.size(); i++) {
            sum += (winner.get(winner.size() - i) * i);
        }
        System.out.println("SUMMMMMMMMMMMMMMMMM " +sum);

        System.out.println(day22.playGameRecursive(player1Copy, player2Copy));
    }
}

