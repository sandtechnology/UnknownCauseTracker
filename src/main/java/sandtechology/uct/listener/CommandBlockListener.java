package sandtechology.uct.listener;

import org.bukkit.Location;
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
            Location location = ((BlockCommandSender) event.getSender()).getBlock().getLocation();
            if (config().getStringList("WorldWhiteList").contains(location.getWorld().getName())) {
                log().info(String.format(
                        getI18n("plugin.track.commandblock"),
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ()));
            }
        }
    }
}
