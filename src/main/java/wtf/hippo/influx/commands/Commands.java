package wtf.hippo.influx.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import wtf.hippo.influx.commands.general.AboutCommand;
import wtf.hippo.influx.commands.general.HelpCommand;

import java.util.ArrayList;
import java.util.List;

public class Commands extends ListenerAdapter {

    private List<BaseCommand> commands;

    public Commands() {
        this.commands = new ArrayList<>();
        this.commands.add(new HelpCommand());
        this.commands.add(new AboutCommand());
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String name = event.getName();
        if(name.equals("help")) HelpCommand.onCommand(event, this.commands);
        if(name.equals("about")) AboutCommand.onCommand(event);
        //Development command is handled in here
        if(name.equals("development")) {
            if(event.getUser().getId().equals("665488298533322762")) event.reply("Development is in progress @everyone").queue();
            else event.reply("Development is in progress").queue();
        }

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getMessage().getAuthor().getId().equals("665488298533322762")) {
            if(event.getMessage().getGuild().getId().equals("861076741460459530")) {
                if(event.getMessage().getContentRaw().equals("<@!869505418563551253> init")) {

                    //Register commands per guild before pushing to global
                    event.getGuild().updateCommands().queue();
                    event.getGuild().upsertCommand("development", "Information about gitter development").queue();

                    for (BaseCommand command : this.commands) {
                        event.getGuild().upsertCommand(command.commandData).queue();
                    }

                    event.getMessage().reply("All done! The commands have been updated").queue();

                }
            }
        }
    }
}
