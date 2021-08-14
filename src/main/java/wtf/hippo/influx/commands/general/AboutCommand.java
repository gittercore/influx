package wtf.hippo.influx.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

public class AboutCommand extends BaseCommand implements InteractionCommand {

    public AboutCommand() {
        this.commandData = new CommandData("about", "Get information")
                .addSubcommands(new SubcommandData("bot", "Get information about the bot"))
                .addSubcommands(new SubcommandData("server", "Get information about the server"))
                .addSubcommands(new SubcommandData("user", "Get information about a user")
                    .addOption(OptionType.USER, "user", "User to get information about", true));
    }

    public static void onCommand(SlashCommandEvent event) {
        event.deferReply().queue();

        if (event.getSubcommandName().equals("bot")) {
            // Because information is being given about the JDA, the JDA must be obtained
            JDA jda = event.getJDA();

            EmbedBuilder about = new EmbedBuilder()
                    .setColor(Color.decode("#5865F2"))
                    .setFooter("Requested by " + event.getUser().getName(), event.getUser().getAvatarUrl())
                    .setTimestamp(LocalDateTime.now())
                    .setDescription("❗ Looks like you've found me!")
                    .setThumbnail(jda.getSelfUser().getAvatarUrl());

            StringBuilder roles = new StringBuilder();
            for (Role role : event.getGuild().getMember(jda.getSelfUser()).getRoles()) {
                roles.append("<@&")
                        .append(role.getId())
                        .append("> ");
            }
            long ping = jda.getGatewayPing();

            about.addField("**Name**", jda.getSelfUser().getAsTag(), true);
            about.addField("**User id**", jda.getSelfUser().getId(), true);

            try {
                if(!event.getGuild().getMember(jda.getSelfUser()).getNickname().equals(null)) {
                    about.addField("**Nickname**", event.getGuild().getMember(jda.getSelfUser()).getNickname(), true);
                }
            } catch (NullPointerException e) {
                about.addField("**Nickname**", "None yet..", true);
            }
            about.addField("**As mention**", jda.getSelfUser().getAsMention(), true);



            about.addField("**Ping**", Long.toString(ping).concat("ms"), true);
            about.addField("**Account type**", "Bot\n`Operating in " + jda.getGuilds().size() + " servers`", true);

            about.addField("**Roles**", roles.toString(), false);

            about.addField("**Join date**", "I joined this server at `" +event.getGuild().getMember(jda.getSelfUser()).getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`", true);
            about.addField("**Account creation**", "This account was made at `" + jda.getSelfUser().getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "`", true);


            event.getHook().sendMessageEmbeds(about.build()).queue();
            return;
        }
        if (event.getSubcommandName().equals("server")) {



        }
    }

}
