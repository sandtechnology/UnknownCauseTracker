package sandtechology.uct.listener;

import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import static sandtechology.uct.UCT.config;
import static sandtechology.uct.UCT.log;
import static sandtechology.uct.language.LanguageManager.getI18n;

public class CommandBlockListener implements Listener {
    @EventHandler
    public void onCommandBlockAct(ServerCommandEvent event) {
        if (event.getSender() instanceof BlockCommandSender) {
            Block commandBlock = ((BlockCommandSender) event.getSender()).getBlock();
            if (!config().getStringList("WorldWhiteList").contains(commandBlock.getWorld().getName())) {
                log().info(String.format(
                        getI18n("plugin.track.commandblock"),
                        commandBlock.getWorld().getName(),
                        commandBlock.getX(),
                        commandBlock.getY(),
                        commandBlock.getZ(),
                        event.getCommand()));
            }
        }
    }
}
