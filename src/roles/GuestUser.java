package roles;

import models.User;

import java.time.LocalDate;

public class GuestUser extends User {
    public GuestUser(int i, String managerUser, String manager123, String manager, String user, LocalDate of, String female) {
        super(i, managerUser, manager123, manager, user, of, female);
    }
}
