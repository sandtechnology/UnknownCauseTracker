package sandtechology.uct.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static sandtechology.uct.UCT.config;
import static sandtechology.uct.UCT.log;
import static sandtechology.uct.language.LanguageManager.getI18n;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommandSent(PlayerCommandPreprocessEvent event) {
        if (config().getBoolean("TrackPlayerCommand")) {
            log().info(String.format(getI18n("plugin.track.command"), event.getPlayer().getName()));
            Thread.dumpStack();
        }
    }
}
