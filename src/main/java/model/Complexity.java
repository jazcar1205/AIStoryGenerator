package model;
public enum Complexity {
    CHILDFRIENDLY("Child Friendly"),
    AVERAGE("Average"),
    COMPLEX("Complex");

    private final String complex;

    Complexity(String complex) {
        this.complex = complex;
    }

    public static Complexity getComplexity(String str)
    {
        String str1 = str.replaceAll("\\s","");
        str1 = str1.toUpperCase();
        return Complexity.valueOf(str1);
    }
    public String toString()
    {
        switch(this)
        {
            case CHILDFRIENDLY:
                return "Child Friendly";
            case AVERAGE:
                return "Average";
            case COMPLEX:
                return "Complex";
        }
        return null;
    }
}