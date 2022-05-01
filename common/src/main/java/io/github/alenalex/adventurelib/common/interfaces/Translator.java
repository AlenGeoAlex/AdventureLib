package io.github.alenalex.adventurelib.common.interfaces;

import io.github.alenalex.adventurelib.common.translator.LegacyTranslator;
import io.github.alenalex.adventurelib.common.translator.MiniMessageTranslator;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Translator {

    /**
     * Translator Implementations
     */
    Translator LEGACY = new LegacyTranslator();
    Translator MINI_MESSAGE = new MiniMessageTranslator();

    Component colorize(@NotNull String textToColor);

    default List<Component> colorize(String... textsToColor){
        final List<Component> colorizedList = new ArrayList<>();
        for(String s : textsToColor){
            if(s == null)
                continue;

            colorizedList.add(colorize(s));
        }
        return colorizedList;
    }

    default List<Component> colorize(Collection<String> textsToColor){
        final List<Component> colorizedList = new ArrayList<>();
        for(String s : textsToColor){
            if(s == null)
                continue;

            colorizedList.add(colorize(s));
        }
        return colorizedList;
    }

    String stripColorCodes(@NotNull Component component);

    default List<String> stripColorCodes(Component... components){
        final List<String> stripedList = new ArrayList<>();
        for(Component component : components){
            if(component == null)
                continue;

            stripedList.add(stripColorCodes(component));
        }
        return stripedList;
    }

    default List<String> stripColorCodes(Collection<Component> components){
        final List<String> stripedList = new ArrayList<>();
        for(Component component : components){
            if(component == null)
                continue;

            stripedList.add(stripColorCodes(component));
        }
        return stripedList;
    }

}
