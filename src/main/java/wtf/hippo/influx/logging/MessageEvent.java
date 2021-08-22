package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.Date;

public class MessageEvent extends Event{

    public Message message;
    private Emoji emoji;

    public MessageEvent(User member, long time, String action, Message message, Emoji emoji) {
        super(member, time, action);
        this.message = message;
        this.emoji = emoji;
    }
    public String getLog() {
        return this.emoji.getAsMention() + " `Time: " + this.getTime() + "` " + this.getUser().getAsTag() + " (" + this.member.getId() + ") " + this.action + "<#" + this.message.getChannel().getId() + ">: " + this.message.getContentRaw();
    }
}
