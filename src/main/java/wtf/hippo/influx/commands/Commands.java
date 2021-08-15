package wtf.hippo.influx.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wtf.hippo.influx.commands.general.AboutCommand;
import wtf.hippo.influx.commands.general.HelpCommand;
import wtf.hippo.influx.commands.general.InviteCommand;
import wtf.hippo.influx.commands.moderation.BanCommand;
import wtf.hippo.influx.commands.moderation.KickCommand;
import wtf.hippo.influx.commands.moderation.PurgeCommand;

import java.util.ArrayList;
import java.util.List;

public class Commands extends ListenerAdapter {

    private final List<BaseCommand> commands;
    private final List<BaseCommand> generalCommands;
    private final List<BaseCommand> modCommands;

    public Commands() {

        this.commands = new ArrayList<>();
        this.generalCommands = new ArrayList<>();
        this.modCommands = new ArrayList<>();

        this.generalCommands.add(new HelpCommand());
        this.generalCommands.add(new AboutCommand());
        this.generalCommands.add(new InviteCommand());

        this.modCommands.add(new PurgeCommand());
        this.modCommands.add(new BanCommand());
        this.modCommands.add(new KickCommand());

        this.commands.add(new HelpCommand());
        this.commands.add(new AboutCommand());
        this.commands.add(new InviteCommand());
        this.commands.add(new PurgeCommand());
        this.commands.add(new BanCommand());
        this.commands.add(new KickCommand());
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String name = event.getName();
        if (name.equals("help")) HelpCommand.onCommand(event, this.generalCommands, this.modCommands);
        if (name.equals("about")) AboutCommand.onCommand(event);
        if (name.equals("purge")) PurgeCommand.onCommand(event);
        if (name.equals("invite")) InviteCommand.onCommand(event);
        if (name.equals("ban")) BanCommand.onCommand(event);
        if (name.equals("kick")) KickCommand.onCommand(event);
        //Development command is handled in here
        if (name.equals("development")) {
            if (event.getUser().getId().equals("665488298533322762"))
                event.reply("Development is in progress @everyone").queue();
            else event.reply("Development is in progress").queue();
        }

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("<@!869505418563551253> init")) {
            if (event.getMessage().getMember().getPermissions().contains(Permission.MANAGE_SERVER)) {

                //Register commands per guild before pushing to global
                event.getGuild().updateCommands().queue();
                event.getGuild().upsertCommand("developement", "Information about gitter development").queue();

                for (BaseCommand command : this.commands) {
                    event.getGuild().upsertCommand(command.commandData).queue();
                }
                event.getMessage().reply("This could talk a few seconds, but I'm updating your commands").queue();
            } else {
                event.getMessage().reply("Looks like you don't have the `MANAGE_SERVER` permission!").queue();
            }

        }
    }
}
