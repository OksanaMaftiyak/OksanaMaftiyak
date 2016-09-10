package lab1;

import java.util.Arrays;

import static java.lang.System.out;


public class variant20 {

    public enum Zodiaс {

        ARIES(21, 3, 19, 4), TAURUS(20, 4, 20, 5), GEMINI(21, 5, 21, 6),
        CANCER(22, 6, 22, 7), LEO(23, 7, 22, 8), VIRGO(23, 8, 22, 9),
        LIBRA(23, 9, 22, 10), SCORPIO(23, 10, 22, 11), SAGITTARIUS(23, 11, 21, 12),
        CAPRICORN(22, 12, 19, 1), AQUARIUS(20, 1, 18, 2), PISCES(19, 2, 20, 3);

        private int startMonth;
        private int startDay;
        private int endMonth;
        private int endDay;

        Zodiaс(int startDay, int startMonth, int endDay, int endMonth) {
            this.startMonth = startMonth;
            this.startDay = startDay;
            this.endMonth = endMonth;
            this.endDay = endDay;
        }

        public boolean isValidDate(int month, int day) {
            return (this.startMonth == month && this.startDay <= day) || (this.endMonth == month && this.endDay >= day);
        }

    }

    public static class Point {

        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

    }

    public int integerNumbersTask(int k) {
        return k / 360;
    }

    public boolean booleanTask(int number) {
        if (number > 999) {
            out.println(" You entered invalid value;");
            return false;
        }
        return ((number / 100) != (number % 10)
                && (number / 100) != (number / 10 % 10)
                && (number / 10) % 10 != (number % 10)
        );
    }

    public double ifTask(double A, double B, double C) {
        if (Math.abs(A - B) > Math.abs(A - C)) {
            return Math.abs(A - C);
        } else {
            return Math.abs(A - B);
        }
    }

    public Zodiaс switchTask(int month, int day) {
        if (month > 12 || day > 31 || month < 1 || day < 1) {
            System.out.println("Invalid value of month or day!!! ");
            return null;
        }
        for (Zodiaс zodiaс : Zodiaс.values()) {
            if (zodiaс.isValidDate(month, day)) {
                return zodiaс;
            }
        }
        return null;
    }

    public double inputOutputTask(Point pointA, Point pointB) {
        return Math.sqrt(
                (pointB.getX() - pointA.getX()) * (pointB.getX() - pointA.getX())
                        + (pointB.getY() - pointA.getY()) * (pointB.getY() - pointA.getY())
        );
    }

    public double forTask(int n) {
        double result = 1.0, lastStep = 1.0;
        for (int i = 1; i <= n; i++) {
            result += lastStep * i;
        }
        return result;
    }

    public boolean whileTask(int n) {
        int variable;
        do {
            variable = n % 10;
            n /= 10;
        } while (variable != 2 && n != 0);
        return (variable == 2);
    }

    public int arrayTask(double[] array) {
        int countMono = 0, countValue = 0;
        for (int i = 0; i < array.length - 1; i++) {
            while (array[i] < array[i + 1] && i < array.length) {
                i++;
                countValue++;
            }
            if (countValue >= 2) {
                countMono++;
            }
            countValue = 0;
        }
        return countMono;
    }

    public int[][] twoDimensionArrayTask(int[][] array) {
        int count = 0;
        int[][] arrayResult;
        for (int[] anArray : array) {
            if (anArray[array.length - 1] < 0) {
                count++;
            }
        }
        if (count == array.length) {
            arrayResult = Arrays.copyOf(array, array.length);
            for (int i = 0; i < array.length; i++) {
                arrayResult[i] = Arrays.copyOf(array[i], array.length - 1);
            }
            return arrayResult;
        }
        return array;
    }

}
