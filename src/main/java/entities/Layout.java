package entities;

public enum Layout {
    STANDART("standard"),
    DELUXE("deluxe"),
    JOINT("joint"),
    SUITE("suite");



    private final String text;

    Layout(String text) {
        this.text = text;
    }

    public static Layout fromString(String text) {
        for (Layout b : Layout.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public String toString() {
        return text;
    }
}
