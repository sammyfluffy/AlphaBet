package AlphaBet.bot;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws LoginException, SQLException {
        Bot bot = new Bot();
        bot.start();
    }
}