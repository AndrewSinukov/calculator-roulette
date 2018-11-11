package as.calculatorrouletteo.models;

public class Roulette {

    private int idR;
    private int fieldR;
    private double rateR;
    private int countBtn;
    private boolean cngVal;

    public Roulette(int i, int i1, double rate, boolean b) {
        this.idR = i;
        this.fieldR = i1;
        this.rateR = rate;
        this.cngVal = b;
    }

    public Roulette(int i) {
        this.fieldR = i;
    }

    public Roulette(double nubr) {
        this.rateR = nubr;
    }

    public boolean getCngVal() {
        return cngVal;
    }

    public void setCngVal(boolean cngVal) {
        this.cngVal = cngVal;
    }

    public int getCountBtn() {
        return countBtn;
    }

    public void setCountBtn(int countBtn) {
        this.countBtn = countBtn;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getFieldR() {
        return fieldR;
    }

    public void setFieldR(int fieldR) {
        this.fieldR = fieldR;
    }

    public double getRateR() {
        return rateR;
    }

    public void setRateR(double rateR) {
        this.rateR = rateR;
    }
}