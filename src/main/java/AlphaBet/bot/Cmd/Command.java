package AlphaBet.bot.Cmd;

public class Command {
    private String name;
    private String description;
    private int arg_length = 0;
    public boolean show_description_on_no_args_passed = false;
    private CommandListener listener;
    private String[] aliases;
    public Command(String name,String description, int arg_length, boolean show_description_on_no_args_passed, CommandListener listener) {
        this.name = name;
        this.arg_length = arg_length;
        this.show_description_on_no_args_passed = show_description_on_no_args_passed;
        this.description = description;
        this.listener=listener;
    }

    public Command(String name,String description, CommandListener listener) {
        this.name = name;
        this.description = description;
        this.listener = listener;
    }

    public Command(String name, String description) {
        this.name=name;
        this.description=description;
    }

    public Command addAliases(String... aliases) {
        this.aliases = aliases;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getArg_length() {
        return arg_length;
    }

    public CommandListener getListener() {
        return listener;
    }

    public String[] getAliases() {
        return aliases;
    }

    public boolean hasAliases() {
        return aliases != null && aliases.length > 0;
    }

    public boolean isAlias(String al) {
        if(!hasAliases()) return false;
        for(String a : aliases) {
            if(a.equalsIgnoreCase(al)) return true;
        }
        return false;
    }

    public String fetchAliases(String space) {
        if (!hasAliases()) return null;
        String res = "";
        for (int i = 0; i < aliases.length; i++) {
            String a = aliases[i];
            res += a;
            if (res.length() > 1 && i!= aliases.length-1) {
                res += space;
            }
        }
        return res;
    }

    public boolean isSlashCommand() {return this instanceof SlashCommand;}
}
