package sandtechology.uct.command;

import net.minecraft.server.v1_7_R1.CommandBlockListenerAbstract;
import net.minecraft.server.v1_7_R1.MinecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;

import static sandtechology.uct.UCT.reload;
import static sandtechology.uct.language.LanguageManager.getI18n;
import static sandtechology.uct.until.MessageUntil.sendMessage;
import static sandtechology.uct.until.MessageUntil.sendStack;

public class CommandHandler implements CommandExecutor {
    public final int dispatchVanillaCommandBlock(CommandBlockListenerAbstract icommandlistener, String s) {
        Plugin plugin = MinecraftServer.getServer().server.getPluginManager().getPlugin("UnknownCauseTracker");
        if (plugin != null) {
            plugin.getLogger().info(
                    String.format(
                            getI18n("plugin.track.commandblock"),
                            icommandlistener.getWorld().getWorld().getName(),
                            icommandlistener.getChunkCoordinates().x,
                            icommandlistener.getChunkCoordinates().y,
                            icommandlistener.getChunkCoordinates().z)
            );
        }
        return 0;
    }

    public boolean dispatch(CommandSender sender, String commandLine) {
        if (sender instanceof BlockCommandSender) {
            sender.getServer().getPluginManager().callEvent(new ServerCommandEvent(sender, commandLine));
        }
        return false;
    }
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
