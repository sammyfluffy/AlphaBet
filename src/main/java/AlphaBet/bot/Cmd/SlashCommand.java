package AlphaBet.bot.Cmd;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class SlashCommand extends Command{
    private SlashCommandListener listener;
    public SlashCommand(String name, String description, SlashCommandListener listener, JDA jda) {
        super(name.toLowerCase(), description);
        this.listener = listener;
        jda.upsertCommand(name.toLowerCase(), description).queue(command -> {
            System.out.println("[SLASH COMMAND] "+name+" has loaded");
        });
    }

    public SlashCommand(CommandData commandData, SlashCommandListener listener, JDA jda) {
        super(commandData.getName().toLowerCase(), commandData.getDescription().toLowerCase());
        this.listener = listener;
        jda.upsertCommand(commandData).queue(command -> {
            System.out.println("[SLASH COMMAND] "+getName()+" has loaded");
        });
    }

    @Override
    public SlashCommandListener getListener() {
        return listener;
    }
}