package wtf.hippo.influx.commands.general;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;

public class AboutCommand extends BaseCommand implements InteractionCommand {

    public AboutCommand() {
        this.commandData = new CommandData("about", "Get information about the bot");
    }

    public static void onCommand(SlashCommandEvent event) {
        // Because information is being given about the JDA, the JDA must be obtained
        JDA jda = event.getJDA();

        event.deferReply().queue();

        // Multi-line String build
        String about = new String()
                .concat("**Influx**\nWatching the chats go by...\n\n")
                .concat("`Operating in " + jda.getGuilds().size() + " servers`\n\n")
                .concat("**My roles**\n");
        for (Role role : event.getGuild().getMember(jda.getSelfUser()).getRoles()) {
            about = about.concat("<@&" + role.getId() + "> ");
        }
        long ping = jda.getGatewayPing();
        about = about.concat("\n\n**Ping** - ").concat(Long.toString(ping)).concat("ms\n\n")
                .concat("*Last updated 14/08/2021*");

        event.getHook().sendMessage(about).queue();
    }

}
