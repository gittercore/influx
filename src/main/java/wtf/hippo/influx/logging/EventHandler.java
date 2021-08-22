package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import wtf.hippo.influx.DataBase;

import java.util.Date;
import java.util.List;

public class EventHandler extends ListenerAdapter {

    // Bulk of event handling is found here

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getAuthor().isBot()) return;
        // TODO: cache guild logging channel in a hashmap
        Document query = new Document()
                .append("_id", event.getGuild().getId());
        List<Document> result = new DataBase().find(query);
        if (result.isEmpty()) {
        } else {
            if (result.get(0).get("message_log").equals(null)) {
                return;
            } else {
                TextChannel channel = event.getJDA().getTextChannelById(result.get(0).get("message_log").toString());
                assert channel != null;
                MessageEvent messageEvent = new MessageEvent(event.getMember().getUser(), new Date().getTime(), "sent a message", event.getMessage(), Emoji.fromUnicode("📝"));
                channel.sendMessage(messageEvent.getLog()).queue();
            }
        }
    }

}
