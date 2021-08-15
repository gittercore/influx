package wtf.hippo.influx.logging;

import net.dv8tion.jda.api.entities.Member;

import java.util.Date;

public class Event {
    public Member member;
    public long time;
    public String action;

    public Event(Member member, long time, String action) {
        this.action = action;
        this.time = time;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public long getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public String getLog() {
        return "hi";
    }
}
