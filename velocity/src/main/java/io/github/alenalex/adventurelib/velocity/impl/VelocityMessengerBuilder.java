package io.github.alenalex.adventurelib.velocity.impl;

import com.velocitypowered.api.proxy.ProxyServer;
import io.github.alenalex.adventurelib.common.interfaces.Messenger;
import io.github.alenalex.adventurelib.common.interfaces.MessengerBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translator;

public class VelocityMessengerBuilder implements MessengerBuilder<ProxyServer> {

    private ProxyServer plugin;
    private Translator defaultTranslator;


    @Override
    public MessengerBuilder<ProxyServer> setPlugin(ProxyServer plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public MessengerBuilder<ProxyServer> defaultToLegacyTranslator() {
        this.defaultTranslator = Translator.LEGACY;
        return this;
    }

    @Override
    public MessengerBuilder<ProxyServer> defaultToMiniMessageTranslator() {
        this.defaultTranslator = Translator.MINI_MESSAGE;
        return this;
    }

    @Override
    public Messenger<?> build() {
        return null;
    }
}
