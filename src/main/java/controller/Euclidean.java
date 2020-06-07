package controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Euclidean {

    private ArrayList<BigInteger> rk = new ArrayList<>();
    private ArrayList<BigInteger> qk = new ArrayList<>();
    private ArrayList<BigInteger> xk = new ArrayList<>();
    private ArrayList<BigInteger> yk = new ArrayList<>();

    public Euclidean(BigInteger init_a, BigInteger init_b) {
        rk.add(init_a);
        rk.add(init_b);
        qk.add(BigInteger.ZERO);
        xk.add(BigInteger.ONE);
        xk.add(BigInteger.ZERO);
        yk.add(BigInteger.ZERO);
        yk.add(BigInteger.ONE);
    }

    public void calc() {
        ea();
        eea();
    }

    private void ea() {
        BigInteger n,m,k,l;
        while (true) {
            n = rk.get(rk.size()-2);
            m = rk.get(rk.size()-1);
            k = n.divide(m);  // k = n/m
            qk.add(k);
            l = n.subtract(k.multiply(m));  // n - (k*m)
            if (l.equals(BigInteger.ZERO)) break;
            rk.add(l);
        }
    }

    private void eea() {
        BigInteger n,m,k,l;
        for (int i = 1; i < rk.size()-1; i++) {
            n = xk.get(i - 1);
            m = xk.get(i);
            k = yk.get(i - 1);
            l = yk.get(i);

            xk.add(n.add(m.multiply(qk.get(i)))); // n + m * qk.get(i)
            yk.add(k.add(l.multiply(qk.get(i))));
        }
    }

    public BigInteger getLnko() {
        return rk.get(rk.size()-1);
    }

    public void printTable() {
        System.out.print("rk: | ");
        rk.forEach(e -> System.out.print(e + " | "));
        System.out.println();
        System.out.print("qk: | ");
        qk.forEach(e -> System.out.print(e + " | "));
        System.out.println();
        System.out.print("xk: | ");
        xk.forEach(e -> System.out.print(e + " | "));
        System.out.println();
        System.out.print("yk: | ");
        yk.forEach(e -> System.out.print(e + " | "));
        System.out.println();
    }

    public BigInteger getXK() {
        // xk.get(xk.size()-1) * Math.pow(-1, xk.size()-1)
        return xk.get(xk.size()-1).multiply(BigDecimal.valueOf(Math.pow(-1, xk.size()-1)).toBigIntegerExact());
    }

    public BigInteger getYK() {
        return yk.get(yk.size()-1).multiply(BigDecimal.valueOf(Math.pow(-1, yk.size())).toBigIntegerExact());
    }
}
