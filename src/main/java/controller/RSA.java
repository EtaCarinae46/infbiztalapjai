package controller;

import java.math.BigInteger;

public class RSA {
    public static BigInteger[] generateKey(BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q); // n = p*q;
        BigInteger fn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // fn = (p-1)*(q-1);
        BigInteger e = findRelPrim(fn);

        Euclidean euclidean = new Euclidean(e,fn);
        euclidean.calc();

        BigInteger d = euclidean.getXK();
        if (d.compareTo(BigInteger.ZERO) < 0) d = d.add(fn); //d += fn;  d < 0
        if (!(d.multiply(e)).mod(fn).equals(BigInteger.ONE)) return null;

        return new BigInteger[]{n,fn,e,d};
    }

    private static BigInteger findRelPrim(BigInteger a) {
        BigInteger i = BigInteger.valueOf(3);
        while(!isRelPrim(i, a)) {
            i = i.add(BigInteger.ONE);
        }
        return i;
    }

    private static boolean isRelPrim(BigInteger a, BigInteger b) {
        Euclidean e = new Euclidean(a,b);
        e.calc();

        return e.getLnko().equals(BigInteger.ONE);
    }
}