package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;

public class Logger {

    private HashMap<Guild, TextChannel> loggingChannel;

    public Logger() {
        this.loggingChannel = new HashMap<Guild, TextChannel>();
    }
    public void log(Log log, Event event) {
        log.log(this.loggingChannel.get(log.guild), event);
    }
    public HashMap<Guild, TextChannel> getLoggingChannel() {
        return this.loggingChannel;
    }
    public HashMap<Guild, TextChannel> addLoggingChannel(Guild guild, TextChannel textChannel) {
        this.loggingChannel.put(guild, textChannel);
        return this.getLoggingChannel();
    }
}
