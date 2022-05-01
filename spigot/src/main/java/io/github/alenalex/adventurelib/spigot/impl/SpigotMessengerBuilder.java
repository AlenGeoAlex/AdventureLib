package io.github.alenalex.adventurelib.spigot.impl;

import io.github.alenalex.adventurelib.common.exception.IllegalBuildingParameter;
import io.github.alenalex.adventurelib.common.interfaces.MessengerBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMessengerBuilder implements MessengerBuilder<JavaPlugin> {

    private JavaPlugin plugin = null;
    private Translator translator = null;

    @Override
    public SpigotMessengerBuilder setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public SpigotMessengerBuilder defaultToLegacyTranslator() {
        this.translator = Translator.LEGACY;
        return this;
    }

    @Override
    public SpigotMessengerBuilder defaultToMiniMessageTranslator() {
        this.translator = Translator.MINI_MESSAGE;
        return this;
    }

    @Override
    public SpigotMessenger build() {
        if(plugin == null)
            throw new IllegalBuildingParameter("You have not provided the plugin!");

        if(translator == null)
            this.translator = Translator.MINI_MESSAGE;

        return new SpigotMessenger(plugin, translator);
    }
}
