package wtf.hippo.influx;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import wtf.hippo.influx.commands.Commands;
import wtf.hippo.influx.utils.KeepAlive;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");
        assert token != null;
        KeepAlive.run();
        JDA bot = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.watching("the chats go by"))
                .setStatus(OnlineStatus.IDLE)
                .addEventListeners(new Commands())
                .build();
        System.out.println(bot.getSelfUser().getName());
    }
}
