package models;

public enum UserRole {
    ADMIN,
    MANAGER,
    EDITOR,
    USER;

    public boolean equalsIgnoreCase(UserRole userRole) {
        if(userRole==ADMIN){
            return true;
        }else {
            return false;
        }
    }
}