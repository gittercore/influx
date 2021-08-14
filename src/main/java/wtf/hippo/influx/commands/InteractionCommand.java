package wtf.hippo.influx.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public interface InteractionCommand {
    static void onCommand(SlashCommandEvent event) {}
}
