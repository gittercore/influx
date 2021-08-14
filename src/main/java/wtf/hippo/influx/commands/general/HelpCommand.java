package wtf.hippo.influx.commands.general;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;
import wtf.hippo.influx.commands.utils.HelpUtils;

import java.util.List;

public class HelpCommand extends BaseCommand implements InteractionCommand {

    public HelpCommand() {
        this.commandData = new CommandData("help", "Get a list of commands and descriptions");
    }

    public static void onCommand(SlashCommandEvent event, List<BaseCommand> commands) {
        event.deferReply().queue();
        String help = HelpUtils.generateHelpCommand(commands);
        event.getHook().sendMessage("```\n" + help + "```").queue();
    }

}
