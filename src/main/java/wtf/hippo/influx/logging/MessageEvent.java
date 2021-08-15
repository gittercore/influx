package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.Date;

public class MessageEvent extends Event{

    public Message message;

    public MessageEvent(Member member, long time, String action, Message message) {
        super(member, time, action);
        this.message = message;
    }
    public String getLog() {
        return "📝 `Time: " + this.getTime() + "` " + this.member.getUser().getAsTag() + " (" + this.member.getId() + ") " + this.action + "<#" + this.message.getChannel().getId() + ">: " + this.message.getContentRaw();
    }
}
