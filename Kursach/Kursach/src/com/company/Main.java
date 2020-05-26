package com.company;

import java.io.Console;

public class Main {

    public static void main(String[] args) {

        StudentDis studentDis = new StudentDis();
        Tests tests = new Tests();
        int n = 1;


        //Testing value
        double x = 0.324920;
        System.out.println("X = "+ x + ", N = " + n + " ,Counted DF: " + (1 - studentDis.testDist(x, n)));
        System.out.println("From table DF: " + tests.getDF(x, n));

        //Counting value
        n = 4;
        double alpha = 0.05;
        System.out.println("N = "+ n + ", alpha = " + alpha + " ,Counted value: " + studentDis.dist(alpha, n));
        System.out.println("From table value: " + tests.getVal(alpha, n));


    }


}
