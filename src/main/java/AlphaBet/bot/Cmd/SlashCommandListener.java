package AlphaBet.bot.Cmd;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public interface SlashCommandListener extends CommandListener {
    void onSlashCommand(SlashCommand command, SlashCommandEvent event);
}