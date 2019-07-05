package sandtechology.uct.until;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static sandtechology.uct.UCT.getPlugin;
import static sandtechology.uct.language.LanguageManager.getI18n;


public class MessageUntil {

    private MessageUntil() {
    }

    public static void sendMessage(CommandSender sender, ChatColor color, String message, Object... format) {
        sender.sendMessage(ChatColor.GREEN + "[UCT]" + color + String.format(message, format));
    }

    public static void sendStack(CommandSender sender) {
        sendMessage(sender, ChatColor.GREEN, getI18n("command.stackdump"));
        Thread server = Thread.currentThread();
        //感谢754503921和a39的建议
        sender.getServer().getScheduler().runTaskAsynchronously(getPlugin(), () -> {
            Arrays.stream(server.getStackTrace()).map(String::valueOf).forEach(sender::sendMessage);
        });

    }

}
