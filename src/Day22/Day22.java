package Day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Day22 {

    LinkedList<Integer> splitInput(String input) {
        LinkedList<Integer> queue = new LinkedList<>();
        String[] nums = input.split("\n");
        for (int i = 1; i < nums.length; i++) {
            queue.add(Integer.parseInt(nums[i]));
        }
        return queue;
    }

    LinkedList<Integer> playGame(LinkedList<Integer> player1, LinkedList<Integer> player2) {
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

        LinkedList<Integer> winner = day22.playGame(player1, player2);

        int sum = 0;
        for (int i = 1; i <= winner.size(); i++) {
            sum += (winner.get(winner.size() - i) * i);
        }
        System.out.println(sum);
    }
}

