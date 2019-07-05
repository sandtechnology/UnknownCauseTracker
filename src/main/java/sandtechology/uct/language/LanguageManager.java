package sandtechology.uct.language;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static sandtechology.uct.UCT.getPlugin;
import static sandtechology.uct.UCT.log;

public class LanguageManager {
    private static Path langFile = getPlugin().getDataFolder().toPath().resolve("message.lang");
    private static HashMap<String, String> langMap = new HashMap<>();

    private LanguageManager() {
    }


    public static String getI18n(String key) {
        return langMap.getOrDefault(key, key);
    }

    public static boolean reloadLangFile() {
        //backup
        HashMap<String, String> backup = langMap;
        langMap.clear();
        if (loadLangFile()) {
            return true;
        }
        else {
            //use backup
            langMap = backup;
            return false;
        }
    }

    public static boolean loadLangFile() {
        if (!langFile.toFile().exists()) {
            getPlugin().saveResource("message.lang", false);
        }
        try {
            Files.readAllLines(langFile)
                    .stream()
                    .map(x -> x.split("=", 2))
                    .filter(x -> x.length == 2)
                    .forEach(x -> langMap.put(x[0], x[1]));

            return true;
        }
        catch (Exception ex) {
            log().severe("Failed to load language file!");
            ex.printStackTrace();
            return false;
        }
    }
}
