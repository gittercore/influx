package wtf.hippo.influx.commands.moderation;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;

public class KickCommand extends BaseCommand implements InteractionCommand {

    public KickCommand() {
        this.commandData = new CommandData("kick", "Kick a member of the server")
                .addOption(OptionType.USER, "user", "User to ban", true)
                .addOption(OptionType.STRING, "reason", "Reason for the ban", false);
    }

    public static void onCommand(SlashCommandEvent event) {
        event.deferReply(true).queue();
        String reason = "Responsible moderator " + event.getUser().getId() + " (" + event.getUser().getAsTag() + ")";
        Member member = event.getOptions().get(0).getAsMember();
        Member caller = event.getMember();
        JDA jda = event.getJDA();
        Guild guild = event.getGuild();

        assert caller != null;
        assert member != null;
        assert guild != null;

        if(caller.getId().equals(member.getId())) {
            event.getHook().sendMessage("You cannot kick yourself!").queue();
            return;
        }
        if(caller.canInteract(member)) {
            if(caller.hasPermission(Permission.KICK_MEMBERS)) {
                if(guild.getMember(jda.getSelfUser()).canInteract(member)) {
                    if(guild.getMember(jda.getSelfUser()).hasPermission(Permission.KICK_MEMBERS) || guild.getMember(jda.getSelfUser()).hasPermission(Permission.BAN_MEMBERS)) {
                        if (event.getOptions().size() == 1) member.kick(reason).queue();
                        else member.kick(event.getOptions().get(1).getAsString() + "\n" + reason).queue();
                        event.getHook().sendMessage("All done!").setEphemeral(true).queue();
                        event.getChannel().sendMessage(member.getUser().getAsTag() + " `" + member.getId() + "` was kicked!").queue();
                    } else {
                        event.getHook().sendMessage("I do not have the `KICK_MEMBERS` permission!").setEphemeral(true).queue();
                    }
                } else {
                    event.getHook().sendMessage("I can't interact with them!").setEphemeral(true).queue();
                }
            } else {
                event.getHook().sendMessage("Looks like you do not have the `KICK_MEMBERS` permission!").setEphemeral(true).queue();
            }
        } else {
            event.getHook().sendMessage("Hm, it seems the person you want banned is above you").setEphemeral(true).queue();
        }
    }

}
