package wtf.hippo.influx;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class InfluxUtils {
    public static void sendDM(User user, TextChannel tc, String message)
    {
        user.openPrivateChannel() // Opening a channel never fails.
                .flatMap(channel -> channel.sendMessage(message))
                .queue(
                        msg -> {
                        },
                        t -> {
                        }
                );
    }

}
