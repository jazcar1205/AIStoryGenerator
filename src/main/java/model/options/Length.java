package model.options;

/**
 * Provides the options for the length.
 */
public enum Length {
    ANY("Any"),
    SHORT("Short"),
    MEDIUM("Medium"),
    LONG("Long");

    private final String label;

    Length(String label) {
        this.label = label;
    }
    public static Length getLength(String str)
    {
        String str1 = str.replaceAll("\\s","");
        str1 = str1.toUpperCase();
        return Length.valueOf(str1);
    }
    public String toString()
    {
        switch(this)
        {
            case SHORT:
                return "Short";
            case MEDIUM:
                return "Medium";
            case LONG:
                return "Long";
            case ANY:
                return "Any";
        }
        return null;
    }
}
