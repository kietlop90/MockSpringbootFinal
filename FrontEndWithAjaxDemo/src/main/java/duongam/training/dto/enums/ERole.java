package duongam.training.dto.enums;

public enum ERole {
    SUPERADMIN("SUPERADMIN"),
    ADMIN("ADMIN"),
    TRAINER("TRAINER"),
    STUDENT("STUDENT");
    private final String text;

    private ERole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
