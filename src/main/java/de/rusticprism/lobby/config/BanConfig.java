package de.rusticprism.lobby.config;

import de.rusticprism.lobby.Lobby;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BanConfig {
    public static void BanPlayer(ProxiedPlayer player,String reason) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/bannedplayers.yml");
        try {
           configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read BannedPlayers file!");
            return;
        }
        configuration.set(player.getUniqueId() + ".Reason", reason);
        configuration.set(player.getUniqueId() + ".Name", player.getName());
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't save BannedPlayers file!");
            return;
        }
    }
    public static String getBanReason(ProxiedPlayer player) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/bannedplayers.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read BannedPlayers file!");
            return null;
        }
        return configuration.getString(player.getUniqueId() + ".Reason");
    }
    public static boolean isPlayerBanned(UUID player) {
        Configuration configuration = null;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/bannedplayers.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read BannedPlayers file!");
           e.printStackTrace();
        }
        if(configuration.get(player.toString()) == null) {
            return false;
        }else return true;

    }
    public static void UnbanPlayer(UUID player) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/bannedplayers.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read BannedPlayers file!");
            return;
        }
        configuration.set(player+ ".Reason", null);
        configuration.set(player + ".Name", null);
        configuration.set(player.toString(), null);
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't save BannedPlayers file!");
            return;
        }
    }
    public static UUID getUUid(String playername) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/users.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
           Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read Users File!");
           return null;
        }
        return UUID.fromString(configuration.getString(playername));
    }
    public static void setUUid(String playername,String uuid) {
        Configuration configuration;
        File file = new File(Lobby.plugin.getDataFolder().getPath(),"data/users.yml");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't read Users File!");
            return;
        }
        configuration.set(playername,uuid);
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,file);
        } catch (IOException e) {
            Lobby.plugin.getLogger().warning(Lobby.prefix + "§cCouldn't save Users File!");
        }
    }
}
