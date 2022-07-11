package ua.gov.mkip.craft.models.enums;

public enum Sex {
    MAN("Чоловік"),
    WOMAN ("Жінка");

    private final String displayValue;

    private Sex (String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
