package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.Date;

public class MemberBanEvent extends MemberEvent {

    public String reason;

    public MemberBanEvent(User member, long time, String action, String reason, Emoji emoji) {
        super(member, time, action, emoji);
        this.reason = reason;
    }

    @Override
    public String getLog() {
        return this.emoji.getAsMention() + " `Time: " + this.getTime() + "` " +  this.member.getAsTag() + " (" + this.member.getId() + ")" + this.action + " for " + this.reason;
    }
}
