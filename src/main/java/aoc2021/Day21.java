package aoc2021;

import aoc2021.tools.Input;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day21 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day21.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    @Getter
    public static class Die100 {
        int rolls = 0;

        public int getNextRoll() {
            return (rolls++ % 100) + 1;
        }
    }

    public static long getPart1(List<String> lines) {
        int player1 = Short.parseShort(lines.getFirst().substring(28));
        int player2 = Short.parseShort(lines.getLast().substring(28));
        int score1 = 0;
        int score2 = 0;
        Die100 die = new Die100();
        while (true) {
            int moves = die.getNextRoll() + die.getNextRoll() + die.getNextRoll();
            player1 = move(player1, moves);
            score1 += player1;
            if (score1 >= 1000) {
                return (long) score2 * die.getRolls();
            }

            moves = die.getNextRoll() + die.getNextRoll() + die.getNextRoll();
            player2 = move(player2, moves);
            score2 += player2;
            if (score2 >= 1000) {
                return (long) score1 * die.getRolls();
            }
        }
    }

    private static int move(int position, int moves) {
        position = (position + moves) % 10;
        return position == 0 ? 10 : position;
    }

    record State(int player1, int player2, int score1, int score2, long universes) {

        public State player1(int moves, int universes) {
            int newPlayer1 = move(player1, moves);
            return new State(newPlayer1, player2, score1 + newPlayer1, score2, universes * this.universes);
        }

        public State player2(int moves, int universes) {
            int newPlayer2 = move(player2, moves);
            return new State(player1, newPlayer2, score1, score2 + newPlayer2, universes * this.universes);
        }
    }

    private static final Map<Integer, Integer> diracRolls = Map.of(
            3, 1,
            4, 3,
            5, 6,
            6, 7,
            7, 6,
            8, 3,
            9, 1);

    public static long getPart2(List<String> lines) {
        int player1 = Integer.parseInt(lines.getFirst().substring(28));
        int player2 = Integer.parseInt(lines.getLast().substring(28));
        List<State> states = new ArrayList<>();
        states.add(new State(player1, player2, 0, 0, 1));
        long universesPlayer1Won = 0;
        long universesPlayer2Won = 0;
        boolean player1sTurn = true;
        while (!states.isEmpty()) {
            List<State> newStates = new ArrayList<>();
            for (State state : states) {
                for (var r : diracRolls.entrySet()) {
                    int moves = r.getKey();
                    int universes = r.getValue();
                    State newState;
                    if (player1sTurn) {
                        newState = state.player1(moves, universes);
                        if (newState.score1() >= 21) {
                            universesPlayer1Won += newState.universes();
                            continue;
                        }
                    } else {
                        newState = state.player2(moves, universes);
                        if (newState.score2() >= 21) {
                            universesPlayer2Won += newState.universes();
                            continue;
                        }
                    }
                    newStates.add(newState);
                }
            }
            player1sTurn = !player1sTurn;
            states = newStates;
        }
        return Math.max(universesPlayer1Won, universesPlayer2Won);
    }
}