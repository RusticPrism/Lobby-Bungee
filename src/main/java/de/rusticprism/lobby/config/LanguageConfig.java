package de.rusticprism.lobby.config;

import de.rusticprism.lobby.Lobby;
import de.rusticprism.lobby.utils.Config;
import de.rusticprism.lobby.utils.Language;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

public class LanguageConfig {

    public static Language getPlayerLanguage(ProxiedPlayer player) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(), "languages/player.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning("Couldn't read Player language File.");
            return null;
        }
        return new Language(player.getUniqueId().toString());
    }

    public static Language getLanguageByName(String languagename) {
        File file = new File(Lobby.plugin.getDataFolder().getPath(), "languages/" + languagename + ".yml");
        if (!file.exists()) {
            return null;
        }
        return new Language(languagename);
    }

    public static String getStringfromLanguage(Language language, String path) {
        File file = new File(Lobby.plugin.getDataFolder().getPath(), "languages/" + language.getLanguagename() + ".yml");
        if (!file.exists()) {
            return null;
        }
        Configuration configuration;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning("Couldn't read Language File.");
            return null;
        }
        return configuration.getString(path);
    }
}
