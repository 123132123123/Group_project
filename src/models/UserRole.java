package models;

public enum UserRole {
    ADMIN,
    MANAGER,
    EDITOR,
    USER;

    public boolean equalsIgnoreCase(UserRole requiredRole) {
    }
}