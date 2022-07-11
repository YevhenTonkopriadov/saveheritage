package ua.gov.mkip.craft.models.enums;

public enum Role {
    USER("Майстер"),
    USERREGISTERS ("Новий користувач"),
    USERADOPED("Верифікований користувач"),
    ADMIN("Адміністратор"),
    MKIP("МКІП");

    private final String displayValue;

    private Role (String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}