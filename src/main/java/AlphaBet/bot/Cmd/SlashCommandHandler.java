package AlphaBet.bot.Cmd;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandHandler extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        CommandHandler ch = CommandHandler.getInstance();
        SlashCommand command = null;
        for (int i = 0; i < ch.getCommands().size(); i++) {
            Command a = ch.getCommands().get(i);
            if(a.isSlashCommand()&&event.getName().equalsIgnoreCase(a.getName())) {
                command = (SlashCommand) a;
                break;
            }
        }
        if(command==null) return;
        command.getListener().onSlashCommand(command, event);
    }
}
