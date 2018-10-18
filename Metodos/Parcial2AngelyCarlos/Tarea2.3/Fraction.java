import java.math.BigDecimal;
import java.math.BigInteger;

// Tarea 2 30/08/18
//Carlos Augusto Amador Manilla A01329447
//Angel Roberto Ruiz Mendoza A01325243

public class Fraction{
    BigInteger num;
    BigInteger den;

    public Fraction (long n, long d){
        this.num = new BigInteger(""+n);
        this.den = new BigInteger(""+d);
        simplify();
    }

    public Fraction (String n, String d){
        this.num = new BigInteger(n);
        this.den = new BigInteger(d);
        simplify();
    }

    public Fraction (long n){
        this.num = new BigInteger(""+n);
        this.den = new BigInteger("1");
    }

    public Fraction (Fraction other){
        this.num = new BigInteger(other.num.toString());
        this.den = new BigInteger(other.den.toString());
    }


    /**
     * Basic operations +, -, * and /
     * Receiving other fractions and longs
     */
    public void add(Fraction other) {
        this.num = this.num.multiply(other.den).add(this.den.multiply(other.num));
        this.den = this.den.multiply(other.den);
        this.simplify();
    }

    public void add(long n) {
        Fraction other = new Fraction(n);
        this.add(other);
    }

    public void subs(Fraction other) {
        this.num = this.num.multiply(other.den).subtract(this.den.multiply(other.num));
        this.den = this.den.multiply(other.den);
        this.simplify();
    }

    public void subs(long n) {
        Fraction other = new Fraction(n);
        this.subs(other);
    }

    public void mult(Fraction other) {
        this.num = this.num.multiply(other.num);
        this.den = this.den.multiply(other.den);
        this.simplify();
    }

    public void mult(long n) {
        Fraction other = new Fraction(n);
        this.mult(other);
    }

    public void div(Fraction other) {
        this.num = this.num.multiply(other.den);
        this.den = this.den.multiply(other.num);
        this.simplify();
    }

    public void div(long n) {
        Fraction other = new Fraction(n);
        this.div(other);
    }

    /**
     * Simplify fraction
     */
    public void simplify(){
        BigInteger mcd;

        do {
            mcd = getMCD(this.num, this.den);
            this.num = this.num.divide(mcd);
            this.den = this.den.divide(mcd);
        } while(mcd.compareTo(new BigInteger("1")) != 0);
    }

    private static BigInteger getMCD(BigInteger a, BigInteger b) {
       // System.out.println("num " + a.toString() + " den " + b.toString());
        return a.abs().compareTo(b.abs()) == 1? getMCDRec(a.abs(), b.abs()): getMCDRec(b.abs(), a.abs());
    } 

    private static BigInteger getMCDRec(BigInteger a, BigInteger b) {
        //System.out.println("a " + a.toString() + " b " + b.toString());
        return b.compareTo(new BigInteger("0")) == 0? a: getMCDRec(b, a.mod(b));
    }

    /**
     * Overwrite
     */
    public String toString(){
        return this.num.toString() + " / " + this.den.toString(); 
    }

    public int compareTo(Fraction other){
        BigInteger a = this.num.multiply(other.den);
        BigInteger b = other.num.multiply(this.den);
        return a.compareTo(b);
    }
}