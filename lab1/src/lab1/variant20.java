package lab1;

import java.util.Objects;

public class Variant20 {

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

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Point point = (Point) obj;
            return Double.compare(point.x, x) == 0 &&
                    Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

    public double inputOutputTask(Point pointA, Point pointB) {
        return Math.sqrt(
                (pointB.getX() - pointA.getX()) * (pointB.getX() - pointA.getX())
                        + (pointB.getY() - pointA.getY()) * (pointB.getY() - pointA.getY())
        );
    }

    public double inputOutputTask(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public int integerNumbersTask(int n) {
        assert n >= 0;
        return n / 3600;
    }

    public boolean booleanTask(int number) {
//        if (number > 999 || number < 100) {
//            System.out.println(" You entered invalid value;");
//            throw new AssertionError();
//        }
        assert number >= 100;
        assert number <= 999;
        return ((number / 100) != (number % 10)
                && (number / 100) != (number / 10 % 10)
                && (number / 10) % 10 != (number % 10));

    }

    public Point ifTask(double A, double B, double C) {
        Point result = new Point(0, 0);
        if (Math.abs(A - B) >= Math.abs(A - C)) {
            result.setX(C);
            result.setY(Math.abs(A - C));
        } else {
            result.setX(B);
            result.setY(Math.abs(A - B));
        }
        return result;
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

    public strictfp double forTask(int n) {
        double result = 0;
        double fact = 1.0;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
            result = result + fact;
        }
        return result;
    }

    public boolean whileTask(int n) {
//        if (n <= 0) {
//            throw new AssertionError();
//        }
        assert n > 0;
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
            while (i < array.length - 1 && array[i] < array[i + 1]) {
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
        int count = 0, j, g, J = -1;
        int[][] arrayResult;
        for (j = 0; j < array[1].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][j] < 0) {
                    count++;
                }
            }
            if (count == array.length) {
                J = j;
            }
            count = 0;
        }
        if (J != -1) {
            arrayResult = new int[array.length][array[0].length - 1];

            for (int i = 0; i < array.length; i++) {

                for (j = 0, g = 0; g < array[i].length - 1; g++, j++) {
                    if (j == J) {
                        j++;
                    }
                    arrayResult[i][g] = array[i][j];

                }
            }


            return arrayResult;
        } else
            return array;
    }

}
