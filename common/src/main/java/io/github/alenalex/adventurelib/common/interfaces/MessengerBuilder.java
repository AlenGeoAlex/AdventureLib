package io.github.alenalex.adventurelib.common.interfaces;

public interface MessengerBuilder<T>{

    MessengerBuilder<T> setPlugin(T plugin);

    MessengerBuilder<T> defaultToLegacyTranslator();

    MessengerBuilder<T> defaultToMiniMessageTranslator();

    Messenger<?> build();

}
