package sandtechology.uct.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static sandtechology.uct.UCT.reload;
import static sandtechology.uct.language.LanguageManager.getI18n;
import static sandtechology.uct.until.MessageUntil.sendMessage;
import static sandtechology.uct.until.MessageUntil.sendStack;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "dumpstack":
                    sendStack(sender);
                    return true;
                case "reload":
                    reload(sender);
                    return true;
                case "help":
                    sendMessage(sender, ChatColor.YELLOW, getI18n("command.help.1"));
                    sendMessage(sender, ChatColor.YELLOW, getI18n("command.help.2"));
                    sendMessage(sender, ChatColor.YELLOW, getI18n("command.help.3"));
                    return true;
            }
        }
        sendMessage(sender, ChatColor.RED, "未知命令，查看帮助请使用/usc help");
        return false;
    }
}
