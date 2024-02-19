package duongam.training.dto.enums;

public enum ERole {
    CUSTOMER("TRAINEE"), ADMIN("ADMIN");
    private final String text;

    private ERole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
