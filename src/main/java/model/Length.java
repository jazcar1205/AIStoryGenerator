package model;

public enum Length {
    SHORT("Short"),
    MEDIUM("Medium"),
    LONG("Long");

    private final String label;

    Length(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
