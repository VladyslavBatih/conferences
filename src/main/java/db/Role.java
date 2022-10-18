package db;

import db.entity.User;

public enum Role {

    MODERATOR, SPEAKER, USER;

    public static Role getRole(User user) {
        return Role.values()[(user.getRoleId())];
    }

    public int getNumber() {
        return this.ordinal() + 1;
    }
}