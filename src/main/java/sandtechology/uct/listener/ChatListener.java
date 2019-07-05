package sandtechology.uct.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static sandtechology.uct.UCT.config;
import static sandtechology.uct.UCT.log;
import static sandtechology.uct.language.LanguageManager.getI18n;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (config().getStringList("Chat").contains(event.getMessage())) {
            log().info(getI18n("plugin.track.chat"));
            Thread.dumpStack();
        }
    }
}
