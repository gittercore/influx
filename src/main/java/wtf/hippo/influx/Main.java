package wtf.hippo.influx;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LoginException, IOException {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");
        assert token != null;
        JDA bot = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.watching("the chats go by"))
                .setStatus(OnlineStatus.IDLE)
                .build();

    }
}
