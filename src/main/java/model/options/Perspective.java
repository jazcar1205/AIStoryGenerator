package model.options;
/**
 * enum for Perspective options.
 */
public enum Perspective {
    ANY("Any"),
    FIRST("1st"),
    THIRD("3rd");

    private final String perspective;

    Perspective(String complex) {
        this.perspective = complex;
    }

    public static Perspective getPers(String str)
    {
        String str1 = str.replaceAll("\\s","");
        str1 = str1.toUpperCase();
        return Perspective.valueOf(str1);
    }
    public String toString()
    {
        switch(this)
        {
            case FIRST:
                return "First";
            case THIRD:
                return "Third";
            case ANY:
                return "Any";
        }
        return null;
    }
}