package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Member;

import java.util.Date;

public class MemberBanEvent extends Event {

    public Member caller;
    public String reason;

    public MemberBanEvent(Member member, Member caller, long time, String action, String reason) {
        super(member, time, action);
        this.caller = caller;
        this.reason = reason;
    }

    @Override
    public String getLog() {
        return Long.toString(this.time);
    }
}
