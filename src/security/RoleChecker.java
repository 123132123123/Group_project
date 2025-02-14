package security;
import models.User;
import models.UserRole;

import java.util.HashMap;
import java.util.Map;

public class RoleChecker {
    private static final Map<String, String[]> rolePermissions = new HashMap<>();

    static {
        rolePermissions.put("Admin", new String[]{"MANAGE_USERS", "VIEW_REPORTS", "MANAGE_PAYMENTS"});
        rolePermissions.put("Manager", new String[]{"VIEW_REPORTS", "MANAGE_PAYMENTS"});
        rolePermissions.put("Guest", new String[]{"VIEW_OWN_DATA"});
    }
    public static boolean isAdmin(User user) {
        return "Admin".equalsIgnoreCase(String.valueOf(user.getRole()));
    }
    public static boolean isManager(User user) {
        return "Manager".equalsIgnoreCase(String.valueOf(user.getRole()));
    }

    public static boolean isGuest(User user) {
        return "Guest".equalsIgnoreCase(String.valueOf(user.getRole()));
    }

    public static boolean hasAccess(User user, String requiredRole) {
        return user.getRole().equalsIgnoreCase(UserRole.valueOf(requiredRole));
    }

    public static boolean hasPermission(User user, String permission) {
        if (user == null || user.getRole() == null) {
            return false;
        }

        String[] permissions = rolePermissions.get(user.getRole());
        if (permissions == null) {
            return false;
        }

        for (String perm : permissions) {
            if (perm.equalsIgnoreCase(permission)) {
                return true;
            }
        }
        return false;
    }
}

