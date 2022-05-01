package io.github.alenalex.adventurelib.common.translator;

import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class LegacyTranslator implements Translator {

    @Override
    public Component colorize(@NotNull String textToColor) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(textToColor);
    }

    @Override
    public String stripColorCodes(@NotNull Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
}
