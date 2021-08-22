package wtf.hippo.influx.commands.general;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import wtf.hippo.influx.InfluxUtils;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;
import wtf.hippo.influx.commands.utils.HelpUtils;

import java.util.List;

public class HelpCommand extends BaseCommand implements InteractionCommand {

    public HelpCommand() {
        this.commandData = new CommandData("help", "Get a list of commands and descriptions");
    }

    public static void onCommand(SlashCommandEvent event, List<BaseCommand> gencommands, List<BaseCommand> modcommands) {
        event.deferReply().queue();
        String gen = HelpUtils.generateHelpCommand(gencommands);
        String mod = HelpUtils.generateHelpCommand(modcommands);
        event.getHook().sendMessage("Here are all my commands. You can always join my support server to find out more```diff\n- general\n" + gen + "\n- moderation\n" + mod + "```").queue();
    }

}
