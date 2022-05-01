package io.github.alenalex.adventurelib.bungee.impl;

import io.github.alenalex.adventurelib.common.exception.IllegalBuildingParameter;
import io.github.alenalex.adventurelib.common.interfaces.Messenger;
import io.github.alenalex.adventurelib.common.interfaces.MessengerBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMessengerBuilder implements MessengerBuilder<Plugin> {

    private Plugin plugin = null;
    private Translator translator = null;

    @Override
    public MessengerBuilder<Plugin> setPlugin(Plugin plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public MessengerBuilder<Plugin> defaultToLegacyTranslator() {
        this.translator = Translator.LEGACY;
        return this;
    }

    @Override
    public MessengerBuilder<Plugin> defaultToMiniMessageTranslator() {
        this.translator = Translator.MINI_MESSAGE;
        return this;
    }

    @Override
    public Messenger<ProxiedPlayer> build() {
        if(plugin == null)
            throw new IllegalBuildingParameter("You have not provided the plugin!");

        if(translator == null)
            this.translator = Translator.MINI_MESSAGE;

        return new BungeeMessenger(plugin, translator);
    }
}
