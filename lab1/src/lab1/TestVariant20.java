package lab1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestVariant20 {

    public static double EPS = 0.0000001;


    @Test(dataProvider = "inputProvider")
    public void inputTest(int x1, int y1, int x2, int y2, double dist) {
        assertEquals(new Variant20().inputOutputTask(x1, y1, x2, y2), dist, EPS);
    }

    @DataProvider
    public Object[][] inputProvider() {
        return new Object[][]{{1, 2, 4, -5, 7.615773106}, {-1, 2, 0, 0, 2.236067977}};
    }


    ////////////////////////////////////////////////

    @Test(dataProvider = "integerProvider")
    public void inputTest(int p1, int p3) {
        assertEquals(new Variant20().integerNumbersTask(p1), p3);
    }

    @DataProvider
    public Object[][] integerProvider() {
        return new Object[][]{{7923, 2}, {3600, 1}, {3601, 1}, {17999, 4}, {0, 0}};
    }

    @Test(expectedExceptions = AssertionError.class, dataProvider = "negativeIntegerProvider")
    public void negativeIntegerTest(int number) {
        new Variant20().integerNumbersTask(number);
    }

    @DataProvider
    public Object[][] negativeIntegerProvider() {
//        return new Object[][]{{86401}, {-2}};
        return new Object[][]{{-86401}, {-2}};
    }


    ////////////////////////////////////////////////

    @Test(dataProvider = "ifProvider")
    public void ifTest(int p1, int p2, int p3, Variant20.Point p4) {
        assertEquals(new Variant20().ifTask(p1, p2, p3), p4);
    }

    @DataProvider
    public Object[][] ifProvider() {
        return new Object[][]{{2, 3, 1, new Variant20.Point(1, 1)},
                {3, 2, 1, new Variant20.Point(2, 1)}, {0, -3, 3, new Variant20.Point(3, 3)}};
    }

    //////////////////////////////////////////////////

    @Test(dataProvider = "booleanProvider")
    public void booleanTest(int p1, boolean p3) {
        assertEquals(new Variant20().booleanTask(p1), p3);
    }

    @DataProvider
    public Object[][] booleanProvider() {
        return new Object[][]{{254, true}, {191, false}, {999, false}};
    }

    @Test(expectedExceptions = AssertionError.class, dataProvider = "negativeBooleanProvider")
    public void negativeBooleanTest(int a) {
//        new Variant20().whileTask(a);
        new Variant20().booleanTask(a);
    }

    @DataProvider
    public Object[][] negativeBooleanProvider() {
        return new Object[][]{{98}, {0}, {5896}};
    }

    //////////////////////////////////////////////////

    @Test(dataProvider = "switchProvider")
    public void switchTest(int day, int month, Variant20.Zodiaс p2) {
        assertEquals(new Variant20().switchTask(day, month), p2);
    }

    @DataProvider
    public Object[][] switchProvider() {
        return new Object[][]{{4, 30, Variant20.Zodiaс.TAURUS}, {12, 31, Variant20.Zodiaс.CAPRICORN}};
    }


    ///////////////////////////////////////////////////

    @Test(dataProvider = "forProvider")
    public strictfp void forTest(int n, double p2) {
        assertEquals(new Variant20().forTask(n), p2, EPS);
    }

    @DataProvider
    public Object[][] forProvider() {
        return new Object[][]{{1, 1.0}, {9, 409113}, {15,  1401602636313.00}};
    }

    ///////////////////////////////////////////////////

    //////////////////////////////////////////

    @Test(dataProvider = "whileProvider")
    public void whileTest(int a, boolean c) {
        assertEquals(new Variant20().whileTask(a), c);
    }

    @DataProvider
    public Object[][] whileProvider() {
        return new Object[][]{{12, true}, {2, true}, {13568, false}};
    }

    @Test(expectedExceptions = AssertionError.class, dataProvider = "negativeWhileProvider")
    public void negativeWhileTest(int a) {
        new Variant20().whileTask(a);
    }

    @DataProvider
    public Object[][] negativeWhileProvider() {
        return new Object[][]{{-1}, {0}};
    }

    //////////////////////////////////////////
    @Test(dataProvider = "arrayProvider")
    public void arrayTest(double[] array, int value) {
        assertEquals(new Variant20().arrayTask(array), value);
    }

    @DataProvider
    public Object[][] arrayProvider() {
        return new Object[][]{{new double[]{3, 2, -1}, 0},
                {new double[]{1, 3, 5, 3, 10, 20, 31}, 2}};
    }


    //////////////////////////////////////////


    @Test(dataProvider = "matrixProvider")
    public void twoDimensionArrayTest(int[][] input, int[][] output) {
        assertEquals(new Variant20().twoDimensionArrayTask(input), output);
    }

    @DataProvider
    public Object[][] matrixProvider() {
        int[][] input1 = {{2, 3, 6, 9, -9},
                {34, 98, -9, 2, -1},
                {-4, 2, 1, 6, -1},
                {-98, 8, 1, 5, -3}};

        int[][] output1 = {{2, 3, 6, 9},
                {34, 98, -9, 2},
                {-4, 2, 1, 6},
                {-98, 8, 1, 5}};

        int[][] input2 = {{-2, 3, -6, 9, 9},
                {-4, 2, -1, 6, -1},
                {34, 98, -9, 2, -1},
                {-98, 8, -1, 5, -3}};

        int[][] output2 = {{-2, 3, 9, 9},
                {-4, 2, 6, -1},
                {34, 98, 2, -1},
                {-98, 8, 5, -3}};

        int[][] input3 = {{-2, 3, -6, 9, 9},
                {-4, 2, -1, 6, -1},
                {-34, 98, 9, 2, -1},
                {-98, 8, -1, 5, -3}};

        int[][] output3 = {{3, -6, 9, 9},
                {2, -1, 6, -1},
                {98, 9, 2, -1},
                {8, -1, 5, -3}};

        int[][] input4 = {{2, 3, -6, 9, 9},
                {-4, 2, -1, 6, -1},
                {-34, 98, 9, 2, -1},
                {-98, 8, -1, 5, -3}};


        return new Object[][]{{input1, output1}, {input2, output2},
                {input3, output3}, {input4, input4}};

    }


}
