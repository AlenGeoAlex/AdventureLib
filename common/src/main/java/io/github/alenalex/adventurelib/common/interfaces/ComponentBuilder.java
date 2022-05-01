package io.github.alenalex.adventurelib.common.interfaces;

public interface ComponentBuilder<T> {

    T build();

    ComponentBuilder<T> clear();

    Class<T> ofClazz();

}
