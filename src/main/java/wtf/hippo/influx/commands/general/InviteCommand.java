package wtf.hippo.influx.commands.general;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.Button;
import wtf.hippo.influx.commands.BaseCommand;
import wtf.hippo.influx.commands.InteractionCommand;

public class InviteCommand extends BaseCommand implements InteractionCommand {

    private final static String PERMISSIONS = "137506401344";
    private final static String CLIENT_ID = "869505418563551253";

    public InviteCommand() {
        this.commandData = new CommandData("invite", "Get the invite to the bot and support server");
    }

    public static void onCommand(SlashCommandEvent event) {
        event.reply("You can invite me using this link")
                .addActionRow(
                        Button.link("https://discord.com/oauth2/authorize?client_id=" + CLIENT_ID + "&permissions=" + PERMISSIONS + "&scope=bot%20applications.commands", "Bot"),
                        Button.link("https://discord.gg/n7EQSvGPax", "Server")
                )
                .queue();
    }

}
