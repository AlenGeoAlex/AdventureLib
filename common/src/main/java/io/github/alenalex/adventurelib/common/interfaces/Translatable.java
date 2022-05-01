package io.github.alenalex.adventurelib.common.interfaces;

public interface Translatable<T> {

    T withLegacyTranslator();

    T withMiniMessageTranslator();

}
