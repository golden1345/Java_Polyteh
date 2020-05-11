package sample;

import java.math.*;

class Program {

    public static void main(String[] args) {
        BigInteger a = new BigInteger("9000000000000000000");
        BigInteger b = new BigInteger("8444444444444444444");
        var average = a.add(b).divide(BigInteger.TWO);
        System.out.println(average);
    }
}