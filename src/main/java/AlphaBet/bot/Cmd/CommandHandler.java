package AlphaBet.bot.Cmd;

import AlphaBet.bot.General.GBVarible;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;

public class CommandHandler extends ListenerAdapter {
    private ArrayList<Command> commands = new ArrayList<>();
    private static CommandHandler instance;

    public static CommandHandler getInstance() {
        if(instance==null) {
            instance = new CommandHandler();
        }
        return instance;
    }
    public boolean registerCommand(Command c) {
        if(commands.contains(c)) {
            return false;
        }
        commands.add(c);
        return true;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String text = event.getMessage().getContentRaw();
        String[] all_raw = text.split(" ");
        if(!text.startsWith(GBVarible.settingStr.get("PREFIX"))) return;
        if(!(all_raw.length>0)) return;
        if(text.isBlank()) return;
        String nameR = all_raw[0];
        String name = nameR.substring(GBVarible.settingStr.get("PREFIX").length());
        for (int i = 0; i < commands.size(); i++) {
            Command c=commands.get(i);
            if(c.hasAliases() && c.isAlias(name) || !c.hasAliases() && c.getName().equalsIgnoreCase(name)) {
                if(c.show_description_on_no_args_passed) {
                    String[] args = text.split(" ");
                    if(args.length<c.getArg_length()) {
                        EmbedBuilder embedB = new EmbedBuilder().setTitle("Ooops wrong arguments...")
                                .addField("Name: ", c.getName(), false)
                                .addField("Description: ", c.getDescription(), false)
                                .setColor(Color.RED);
                        if(c.hasAliases()) {
                            embedB.addField("Aliases: ", c.fetchAliases(", "), false);
                        }
                        //event.getChannel().sendMessageEmbeds(embedB.build()).queue();
                        System.out.println("Hello there!!!");
                    }
                    c.getListener().onCommand(c, args, event);
                } else {
                    if(c.getArg_length()<1) {
                        c.getListener().onCommand(c, null, event);
                    } else {
                        String[] args = text.split(" ");
                        c.getListener().onCommand(c, args, event);
                    }
                }
                break;
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
