package roles;

import models.User;

import java.time.LocalDate;

public class AdminUser extends User {

    public AdminUser(int i, String adminUser, String admin123, String admin, String user, LocalDate of, String male) {
        super(i, adminUser, admin123, admin, user, of,  male);
    }
}
