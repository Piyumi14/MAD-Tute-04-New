package Database;

import android.provider.BaseColumns;

public final class UserMaster {
    private UserMaster() {
    }

    protected static class User implements BaseColumns{
        public static final String TABLE = "Users";
        public static final String COLUsername = "Username";
        public static final String COLPassword = "Password";
    }
}
