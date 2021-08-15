package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import wtf.hippo.influx.DataBase;

import java.util.Date;
import java.util.List;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getMessage().getAuthor().isBot()) return;
        Document query = new Document()
                .append("_id", event.getGuild().getId());
        List<Document> result = new DataBase().find(query);
        if(result.isEmpty()) {
        } else {
            if(result.get(0).get("message_log").equals(null)) {
                return;
            } else {
                TextChannel channel = event.getJDA().getTextChannelById(result.get(0).get("message_log").toString());
                assert channel != null;
                MessageEvent messageEvent = new MessageEvent(event.getMember(), new Date().getTime(), "sent a message", event.getMessage());
                channel.sendMessage(messageEvent.getLog()).queue();
            }
        }


    }
}
