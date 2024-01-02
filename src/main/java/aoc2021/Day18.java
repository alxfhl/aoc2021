package aoc2021;

import aoc2021.tools.Input;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Day18 {

    public static void main(String[] args) {
        final List<String> input = Input.forDay(Day18.class);
        System.out.println("part 1: " + getPart1(input));
        System.out.println("part 2: " + getPart2(input));
    }

    @Getter
    @Setter
    public static abstract class Snail {
        static Snail valueOf(String s) {
            if (s.startsWith("[")) {
                StringBuilder leftString = new StringBuilder();
                int open = 0;
                int x = 1;
                while (true) {
                    char next = s.charAt(x++);
                    if (next == ',' && open == 0) {
                        break;
                    }
                    leftString.append(next);
                    if (next == '[') {
                        open++;
                    } else if (next == ']') {
                        open--;
                    }
                }
                Snail left = valueOf(leftString.toString());
                Snail right = valueOf(s.substring(leftString.length() + 2, s.length() - 1));
                return new SnailPair(left, right);
            }
            return new SnailNumber(Long.parseLong(s));
        }

        private SnailPair parent;

        long getDepth() {
            return parent == null ? 0 : parent.getDepth() + 1;
        }

        void reduce() {
            while (true) {
                SnailPair exploding = getExploding();
                if (exploding != null) {
                    exploding.explode();
                    continue;
                }
                SnailNumber splitting = getSplitting();
                if (splitting != null) {
                    splitting.split();
                    continue;
                }
                break;
            }
        }

        abstract long magnitude();

        abstract SnailPair getExploding();

        abstract SnailNumber getSplitting();

        abstract SnailNumber getRightmostNumber();

        abstract SnailNumber getLeftmostNumber();
    }

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    public static class SnailNumber extends Snail {
        private long number;

        public SnailNumber(long number) {
            this.number = number;
        }

        @Override
        public long magnitude() {
            return number;
        }

        @Override
        SnailPair getExploding() {
            return null;
        }

        @Override
        SnailNumber getSplitting() {
            return number >= 10 ? this : null;
        }

        @Override
        SnailNumber getLeftmostNumber() {
            return this;
        }

        @Override
        SnailNumber getRightmostNumber() {
            return this;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }

        public void split() {
            long left = number / 2;
            long right = number - left;
            SnailPair pair = new SnailPair(new SnailNumber(left), new SnailNumber(right));
            if (getParent().getLeft() == this) {
                getParent().setLeft(pair);
            } else {
                getParent().setRight(pair);
            }
        }
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class SnailPair extends Snail {
        private Snail left;
        private Snail right;

        public SnailPair(Snail left, Snail right) {
            setLeft(left);
            setRight(right);
        }

        public void setLeft(Snail left) {
            this.left = left;
            left.setParent(this);
        }

        public void setRight(Snail right) {
            this.right = right;
            right.setParent(this);
        }

        @Override
        public long magnitude() {
            return 3 * left.magnitude() + 2 * right.magnitude();
        }

        @Override
        SnailPair getExploding() {
            if (left instanceof SnailNumber && right instanceof SnailNumber && getDepth() >= 4) {
                return this;
            }
            SnailPair exploding = left.getExploding();
            return exploding != null ? exploding : right.getExploding();
        }

        @Override
        SnailNumber getSplitting() {
            SnailNumber splitting = left.getSplitting();
            return splitting != null ? splitting : right.getSplitting();
        }

        public void explode() {
            if (left instanceof SnailNumber leftNumber && right instanceof SnailNumber rightNumber && getDepth() >= 4) {
                SnailPair parent = getParent();
                SnailNumber nextLeft = parent.getNextLeftNumber(this);
                if (nextLeft != null) {
                    nextLeft.setNumber(nextLeft.getNumber() + leftNumber.getNumber());
                }
                SnailNumber nextRight = parent.getNextRightNumber(this);
                if (nextRight != null) {
                    nextRight.setNumber(nextRight.getNumber() + rightNumber.getNumber());
                }
                if (parent.getLeft() == this) {
                    parent.setLeft(new SnailNumber(0));
                } else {
                    parent.setRight(new SnailNumber(0));
                }
            } else {
                throw new IllegalStateException();
            }
        }

        private SnailNumber getNextLeftNumber(SnailPair caller) {
            if (caller == right) {
                return left.getRightmostNumber();
            }
            if (caller == left) {
                return getParent() != null ? getParent().getNextLeftNumber(this) : null;
            }
            throw new IllegalStateException();
        }

        private SnailNumber getNextRightNumber(SnailPair caller) {
            if (caller == left) {
                return right.getLeftmostNumber();
            }
            if (caller == right) {
                return getParent() != null ? getParent().getNextRightNumber(this) : null;
            }
            throw new IllegalStateException();
        }

        @Override
        SnailNumber getRightmostNumber() {
            return right.getRightmostNumber();
        }

        @Override
        SnailNumber getLeftmostNumber() {
            return left.getLeftmostNumber();
        }

        @Override
        public String toString() {
            return "[" + left + "," + right + "]";
        }
    }

    public static long getPart1(List<String> input) {
        return input.stream()
                .map(Snail::valueOf)
                .reduce((a, b) -> {
                    SnailPair sum = new SnailPair(a, b);
                    sum.reduce();
                    return sum;
                })
                .map(Snail::magnitude).orElseThrow();
    }

    public static long getPart2(List<String> input) {
        long largest = 0;
        for (int a = 0; a < input.size() - 1; a++) {
            for (int b = a + 1; b < input.size(); b++) {
                SnailPair sum = new SnailPair(Snail.valueOf(input.get(a)), Snail.valueOf(input.get(b)));
                sum.reduce();
                largest = Math.max(largest, sum.magnitude());
                sum = new SnailPair(Snail.valueOf(input.get(b)), Snail.valueOf(input.get(a)));
                sum.reduce();
                largest = Math.max(largest, sum.magnitude());
            }
        }
        return largest;
    }
}