package de.rusticprism.lobby.utils;

import de.rusticprism.lobby.Lobby;
import de.rusticprism.lobby.config.LanguageConfig;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class User {
   private ProxiedPlayer player;
   public User(ProxiedPlayer player) {
       this.player = player;
   }
   public ProxiedPlayer getPlayer() {
       return player;
   }
   public Language getLanguage() {
       return LanguageConfig.getPlayerLanguage(player);
   }
   public static User getUserbyName(String name) {
       return Lobby.USERS.get(Lobby.plugin.getProxy().getPlayer(name).getUniqueId());
   }
}
