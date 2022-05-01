package io.github.alenalex.adventurelib.common.translator;

import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class MiniMessageTranslator implements Translator {

    private final MiniMessage miniMessage;

    public MiniMessageTranslator(){
        this.miniMessage = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.decorations())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.clickEvent())
                        .resolver(StandardTags.keybind())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.insertion())
                        .resolver(StandardTags.font())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.rainbow())
                        .resolver(StandardTags.reset())
                        .build())
                .strict(false)
                .build();
    }


    @Override
    public Component colorize(@NotNull String textToColor) {
        return miniMessage.deserialize(textToColor);
    }

    @Override
    public String stripColorCodes(@NotNull Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
}
