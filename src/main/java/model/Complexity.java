package model;
public enum Complexity {
    CHILDFRIENDLY("Child Friendly"),
    AVERAGE("Average"),
    DIFFICULT("Complex");

    private final String complex;

    Complexity(String complex) {
        this.complex = complex;
    }

    public String getComplex() {
        return complex;
    }
}