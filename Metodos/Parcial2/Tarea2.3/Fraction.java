// Tarea 2 30/08/18
//Carlos Augusto Amador Manilla A01329447
//Angel Roberto Ruiz Mendoza A01325243

public class Fraction{
    long num;
    long den;

    public Fraction (long n, long d){
        this.num = n;
        this.den = d;
        simplify();
    }

    public Fraction (long n)
    {
        this.num = n;
        this.den = 1;
    }

    /**
     * Basic operations +, -, * and /
     * Receiving other fractions and longs
     */
    public void add(Fraction other) {
        this.num = this.num*other.den + this.den*other.num;
        this.den *= other.den;
        this.simplify();
    }

    public void add(long n) {
        Fraction other = new Fraction(n);
        this.add(other);
    }

    public void subs(Fraction other) {
        this.num = this.num*other.den - this.den*other.num;
        this.den *= other.den;
        this.simplify();
    }

    public void subs(long n) {
        Fraction other = new Fraction(n);
        this.subs(other);
    }

    public void mult(Fraction other) {
        this.num *= other.num;
        this.den *= other.den;
        this.simplify();
    }

    public void mult(long n) {
        Fraction other = new Fraction(n);
        this.mult(other);
    }

    public void div(Fraction other) {
        this.num *= other.den;
        this.den *= other.num;
        this.simplify();
    }

    public void div(long n) {
        Fraction other = new Fraction(n);
        this.div(other);
    }

    /**
     * Other operations
     */
    public void powTwo() {
        this.num *= this.num;
        this.den *= this.den;
        this.simplify();
    }

    public double sqrt() {
        return this.sqrt(this.num) / this.sqrt(this.den);
    }

    public double sqrt(long n) {
        double a = (double) n;
        double x = 1;

        for(int i=0; i<n; i++) {
            x = 0.5 * (x + (a/x)); 
        }

        return x;
    }

    /**
     * Simplify fraction
     */
    public void simplify(){
        long mcd;

        do {
            mcd = getMCD(this.num, this.den);
            this.num /= mcd;
            this.den /= mcd;
        } while(mcd != 1);
    }

    private static long abs(long a) {
        if(a < 0) {
            return a*-1;
        }

        return a;
    }

    private static long getMCD(long a, long b) {
        return abs(a) > abs(b)? getMCDRec(abs(a), abs(b)): getMCDRec(abs(b), abs(a));
    } 

    private static long getMCDRec(long a, long b) {
        return b == 0? a: getMCDRec(b, a%b);
    }

    /**
     * Overwrite
     */
    public String toString(){
        return this.num + " / " + this.den; 
    }
}