package wtf.hippo.influx.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public abstract class BaseCommand {

    public CommandData commandData;

    public CommandData getCommandData() {
        return this.commandData;
    }
}
