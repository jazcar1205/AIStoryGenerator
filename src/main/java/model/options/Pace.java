package model.options;

/**
 * enum for Pace options.
 */
public enum Pace {
    ANY("Any"),
    SLOW("Slow"),
    NORMAL("Normal"),
    FAST("Fast");

    private final String pace;

    Pace(String complex) {
        this.pace = complex;
    }

    public static Pace getPace(String str)
    {
        String str1 = str.replaceAll("\\s","");
        str1 = str1.toUpperCase();
        return Pace.valueOf(str1);
    }
    public String toString()
    {
        switch(this)
        {
            case SLOW:
                return "Slow";
            case NORMAL:
                return "Normal";
            case FAST:
                return "Fast";
            case ANY:
                return "Any";
        }
        return null;
    }
}
