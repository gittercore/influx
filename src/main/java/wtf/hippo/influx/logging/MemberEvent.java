package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class MemberEvent extends Event{
    public Emoji emoji;

    public MemberEvent(User user, long time, String action, Emoji emoji) {
        super(user, time, action);
        this.emoji = emoji;
    }
    public String getLog() {
        return this.emoji.getAsMention() + " `Time: " + this.getTime() + "` " + this.getUser().getAsTag() + " (" + this.member.getId() + ") " + this.action;
    }
}
