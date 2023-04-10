package tt.authorization.enums;

public enum Role {

    ADMIN("admin"),
    USER("user");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
