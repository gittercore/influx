package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public abstract class Log {
    public Guild guild;
    public void log(TextChannel channel, Event event) {
        channel.sendMessage(event.getLog()).queue();
    }
}
