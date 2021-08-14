package wtf.hippo.influx.commands.utils;

import wtf.hippo.influx.commands.BaseCommand;

import java.util.List;

public class HelpUtils {
    public static String generateHelpCommand(List<BaseCommand> commands) {
        StringBuilder helpText = new StringBuilder();
        for (BaseCommand command : commands) {
            // Influx's help command is heavily formatted a PR to make this code faster is appreciated
            helpText.append(" ");
            helpText.append(command.getCommandData().getName());
            for (int i = 0; i < 8 - command.getCommandData().getName().length(); i++) {
                helpText.append(' ');
            }
            helpText.append(command.getCommandData().getDescription());
            helpText.append("\n");
        }
        return helpText.toString();
    }
}
