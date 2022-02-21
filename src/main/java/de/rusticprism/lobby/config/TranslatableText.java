package de.rusticprism.lobby.config;

import de.rusticprism.lobby.utils.User;

public class TranslatableText {

    public static String translate(User user, String translatabletext) {
        return LanguageConfig.getStringfromLanguage(user.getLanguage(),translatabletext);
    }
}
