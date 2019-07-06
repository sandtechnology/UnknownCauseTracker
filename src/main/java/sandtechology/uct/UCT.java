package sandtechology.uct;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import sandtechology.uct.command.CommandHandler;
import sandtechology.uct.command.CommandTabCompleter;
import sandtechology.uct.listener.ChatListener;
import sandtechology.uct.listener.CommandBlockListener;
import sandtechology.uct.listener.CommandListener;

import java.util.logging.Logger;

import static sandtechology.uct.language.LanguageManager.*;
import static sandtechology.uct.until.MessageUntil.sendMessage;

public class UCT extends JavaPlugin {


    private static UCT plugin;
    private static Logger logger;
    private static FileConfiguration config;

    public static FileConfiguration config() {
        if (config != null) {
            return config;
        }
        else throw new IllegalStateException("Please wait until plugin started!");
    }

    public static UCT getPlugin() {
        if (plugin != null) {
            return plugin;
        }
        else throw new IllegalStateException("Please wait until plugin started!");
    }

    public static Logger log() {
        if (logger != null) {
            return logger;
        }
        else throw new IllegalStateException("Please wait until plugin started!");
    }

    public static void reload(CommandSender sender) {
        HandlerList.unregisterAll(plugin);
        sendMessage(sender, ChatColor.GREEN, getI18n("command.reload.doing"));
        plugin.load();
        if (reloadLangFile()) {
            sendMessage(sender, ChatColor.GREEN, getI18n("command.reload.succeed"));
        }
        else {
            sendMessage(sender, ChatColor.RED, getI18n("command.reload.failed"));
        }
    }

    private void load() {
        loadLangFile();
        saveDefaultConfig();
        config = getConfig();

        if (config().getBoolean("TrackCommandBlock"))
            getServer().getPluginManager().registerEvents(new CommandBlockListener(), this);
        if (config().getBoolean("TrackPlayerCommand"))
            getServer().getPluginManager().registerEvents(new CommandListener(), this);
        if (config().getBoolean("TrackChat"))
            getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onEnable() {
        plugin = this;
        logger = getLogger();
        load();
        getCommand("uct").setExecutor(new CommandHandler());
        getCommand("uct").setTabCompleter(new CommandTabCompleter());
        log().info(getI18n("plugin.enabled"));
    }

    @Override
    public void onDisable() {
        log().info(getI18n("plugin.disabled"));
    }

}
