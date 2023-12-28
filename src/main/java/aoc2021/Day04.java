package aoc2021;

import aoc2021.tools.Grid;
import aoc2021.tools.Input;
import aoc2021.tools.Parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day04 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day04.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    public record Board(Grid<Integer> numbers, Grid<Boolean> marked) {

        public void mark(Integer draw) {
            for (int x = 0; x < numbers.getWidth(); x++) {
                for (int y = 0; y < numbers.getHeight(); y++) {
                    if (numbers.get(x, y).equals(draw)) {
                        marked.set(x, y, true);
                    }
                }
            }
        }

        public boolean wins() {
            List<List<Boolean>> possibilities = new ArrayList<>();
            for (int x = 0; x < 5; x++) {
                possibilities.add(marked.getRow(x));
                possibilities.add(marked.getColumn(x));
                // possibilities.addAll(marked.getDiagonals());
            }
            return possibilities.contains(List.of(true, true, true, true, true));
        }

        public long getScore(int draw) {
            long sum = 0;
            for (int x = 0; x < numbers.getWidth(); x++) {
                for (int y = 0; y < numbers.getHeight(); y++) {
                    if (!marked.get(x, y)) {
                        sum += numbers.get(x, y);
                    }
                }
            }
            return sum * draw;
        }
    }

    public static long getPart1(List<String> lines) {
        List<Integer> draws = Parse.getIntegers(lines.getFirst());
        List<Board> boards = parseBoards(lines);
        for (Integer draw : draws) {
            for (Board board : boards) {
                board.mark(draw);
                if (board.wins()) {
                    return board.getScore(draw);
                }
            }
        }
        return 0;
    }

    private static List<Board> parseBoards(List<String> lines) {
        int nBoards = lines.size() / 6;
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < nBoards; i++) {
            List<List<Integer>> numbers = lines.subList(i * 6 + 2, i * 6 + 7).stream().map(Parse::getIntegers).toList();
            boards.add(new Board(Grid.valueOf(numbers), new Grid<>(5, 5, false)));
        }
        return boards;
    }

    public static long getPart2(List<String> lines) {
        List<Integer> draws = Parse.getIntegers(lines.getFirst());
        List<Board> boards = parseBoards(lines);
        long lastScore = 0;
        for (Integer draw : draws) {
            Iterator<Board> it = boards.iterator();
            while (it.hasNext()) {
                Board board = it.next();
                board.mark(draw);
                if (board.wins()) {
                    lastScore = board.getScore(draw);
                    it.remove();
                }
            }
            if (boards.isEmpty()) {
                return lastScore;
            }
        }
        return 0;
    }
}