package wtf.hippo.influx.commands.moderation;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;

import java.util.ArrayList;

public class PurgeCommand extends BaseCommand implements InteractionCommand {
    public PurgeCommand() {
        this.commandData = new CommandData("purge", "Clean up a mess")
                .addSubcommands(new SubcommandData("messages", "Clean up the mess (messages)")
                        .addOption(OptionType.INTEGER, "number", "number of messages to delete", true
                ));
    }
    public static void onCommand(SlashCommandEvent event) {
        if(event.getSubcommandName().equals("messages")) {
            if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                long max = event.getOptions().get(0).getAsLong();
                event.deferReply().queue();
                TextChannel channel = event.getTextChannel();
                if (!channel.hasLatestMessage()) {
                    event.getHook().sendMessage("There are no messages to delete!").queue();
                    return;
                }
                event.getHook().sendMessage("Deleting messages...").queue(
                        message -> {
                            channel.getHistoryBefore(message.getId(), (int) max).queue(
                                    response -> {
                                        channel.deleteMessages(response.getRetrievedHistory()).queue();
                                    }
                            );
                            message.editMessage("I've deleted `" + max + "` messages!").queue();
                        }
                );

            } else {
                event.reply("Looks like you do not have the `MESSAGE_MANAGE` permission!").queue();
            }
        }
    }
}
