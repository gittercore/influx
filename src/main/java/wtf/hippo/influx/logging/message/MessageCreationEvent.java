package wtf.hippo.influx.logging.message;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import wtf.hippo.influx.logging.MessageEvent;

public class MessageCreationEvent extends MessageEvent {
    public MessageCreationEvent(User member, long time, String action, Message message) {
        super(member, time, action, message, Emoji.fromUnicode("📝"));
    }
}
