package io.github.alenalex.adventurelib.common.interfaces;

public interface ComponentBuilder<T> extends Cloneable {

    T build();

    ComponentBuilder<T> resetBuilder();

    Class<T> ofClazz();



}
