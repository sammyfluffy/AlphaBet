package AlphaBet.bot.Cmd;

import AlphaBet.bot.Cmd.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface CommandListener {
    void onCommand(Command command, String[] args, GuildMessageReceivedEvent event);
}
