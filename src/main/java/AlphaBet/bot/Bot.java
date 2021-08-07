package AlphaBet.bot;

import AlphaBet.bot.Database.MySql;
import AlphaBet.bot.General.GBVarible;
import AlphaBet.bot.General.SetCon;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* ======================================================================================
====================================================================================== */
public class Bot {
    private static MySql mySql;

    public Bot(){

    }
    /* ======================================================================================
    ====================================================================================== */
    public void start() throws LoginException, SQLException {
        List<GatewayIntent> gatewayIntent = new ArrayList<>();
        gatewayIntent.add(GatewayIntent.GUILD_MEMBERS);
        gatewayIntent.add(GatewayIntent.GUILD_MESSAGES);

        JDABuilder jda = JDABuilder.createDefault(SetCon.TK);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.enableIntents(gatewayIntent);

        mySql = new MySql(SetCon.HOST , SetCon.PORT, SetCon.USER, SetCon.PASSWORD, SetCon.DATABASE).initialize();

        /* ----------------- Connecting to other modules -------------------- */
        GBVarible gbVarible = new GBVarible();//set up global variable
        jda.addEventListeners(gbVarible);

        /* ---------------------------- Set status ---------------------------- */
        jda.setStatus(OnlineStatus.DO_NOT_DISTURB);
//        jda.setActivity(Activity.watching("s!help | AlphaBet"));
        jda.setActivity(Activity.playing("Binary count at AlphaBet(s!)"));
        jda.build();
    }
    /* ======================================================================================
    ====================================================================================== */
    public static MySql getMySql() {
        return mySql;
    }
}