package AlphaBet.bot.General;

import AlphaBet.bot.Bot;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* ======================================================================================
-------------------------------------------------------------------------------------- */
public class GBVarible extends ListenerAdapter {
    public final static boolean ISPRODUCTION  = true;
    public final static Map<String,String> settingStr = new HashMap<>();//General setting
    public final static Map<String,String> ROLEMAP = new HashMap<>();//for role role name (Gold I, Gold II), role id
    public static int currentBinary = 0;//this will get from file when it first load

    public void onReady(ReadyEvent e){
        StringBuilder sb = new StringBuilder();
        ReadingFile();

        settingStr.put("PREFIX","s!");
        settingStr.put("HELP",settingStr.get("PREFIX") + "help");//help command
        settingStr.put("SCORE",settingStr.get("PREFIX") + "sc");//adding score in to table
        settingStr.put("RESULT",settingStr.get("PREFIX") + "sh");//display dashboard
        settingStr.put("PING",settingStr.get("PREFIX") + "ping");//display dashboard
        settingStr.put("SAY",settingStr.get("PREFIX") + "say");//display dashboard
        settingStr.put("BAN",settingStr.get("PREFIX") + "ban");//display dashboard
        /* ------------------------ Channel ------------------------- */
        settingStr.put("BINARYCOUNT","binary_counting");
        settingStr.put("TASKCHN","tasks_submission");//channel to display the score when adding it
        settingStr.put("DASHBOARD","dashboard");//channel to display the dashboard

        settingStr.put("JOIN","join");//get data from this channel to insert to db
        settingStr.put("LEAVE","leave");//get data from this channel to insert to db

        settingStr.put("LOGSSTR","logs");//display log use to get the channels by the name
        settingStr.put("LOGSID","720160026148339762");// this is channel to display the log from BOT

        if (ISPRODUCTION) {
            settingStr.put("SCOREKEEPER","748573984504807425");//scorekeeper in main
        } else {
            settingStr.put("SCOREKEEPER", "786227169491157000");//owner role in testing
        }
        /* ------------ Setting up the reward role ---------------------- */
        ROLEMAP.put("Legend","755160379705000007");
        ROLEMAP.put("Master I","755159592413036605");
        ROLEMAP.put("Master II","755158832464003102");
        ROLEMAP.put("Master III","755158340845305942");
        ROLEMAP.put("Champion I","745583168924549121");
        ROLEMAP.put("Champion II","745582941513580556");
        ROLEMAP.put("Champion III","745581973686452264");
        ROLEMAP.put("Diamond I","733197770017603594");
        ROLEMAP.put("Diamond II","733197294958018632");
        ROLEMAP.put("Diamond III","733195444334624818");
        ROLEMAP.put("Gold I","729686651072151553");
        ROLEMAP.put("Gold II","729686339955720282");
        ROLEMAP.put("Gold III","729685864652734565");
        ROLEMAP.put("Silver I","727231497807069225");
        ROLEMAP.put("Silver II","727231044008411159");
        ROLEMAP.put("Silver III","727230127033024583");
        ROLEMAP.put("Bronze I","726778416694820916");
        ROLEMAP.put("Bronze II","726777914749747221");
        ROLEMAP.put("Bronze III","726774815129862205");
        /* ------------ Setting up the reward role ---------------------- */
        e.getJDA().getGuilds().forEach(guild ->
                sb.append("|  - \"" + guild.getName() + "\" - {@"
                        + guild.getOwner().getUser().getName() + "#"
                        + guild.getOwner().getUser().getDiscriminator() + "} - ["
                        + guild.getId() + "] \n"));

        System.out.println(String.format(
                "#-------------------------------------------\n" +
                        "| %s is ready - Number of guilds: %s \n" +
                        "%s" +
                        "#-------------------------------------------\n",
                e.getJDA().getSelfUser().getAsTag(),e.getJDA().getGuilds().size(), sb));
        String t = Bot.getMySql().getData("tblScore","memberID","616969228972458008","point");
//        System.out.print("return from Db: " + t);
    }
    /* -----------------------------------------------------------------------------
            Reading Binary number from file
    ----------------------------------------------------------------------------- */
    private void ReadingFile(){
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("Binary.txt"));
            String line = readFile.readLine();
            while (line != null) {
                currentBinary = Integer.parseInt(line);
                line = readFile.readLine();
            }
            readFile.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}