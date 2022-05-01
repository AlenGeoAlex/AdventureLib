package io.github.alenalex.adventurelib.common.builder;

import io.github.alenalex.adventurelib.common.interfaces.ComponentBuilder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public class SoundBuilder implements ComponentBuilder<Sound> {

    public static SoundBuilder builder(){
        return new SoundBuilder();
    }

    private String key;
    private String subKey;
    private Sound.Source source;
    private float radius;
    private float pitch;

    private SoundBuilder() {
        this.key = null;
        this.source = null;
        this.subKey = null;

        this.radius = 1.0f;
        this.pitch = 1.0f;
    }

    public String getKey() {
        return key;
    }

    public SoundBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public String getSubKey() {
        return subKey;
    }

    public SoundBuilder setSubKey(String subKey) {
        this.subKey = subKey;
        return this;
    }

    public Sound.Source getSource() {
        return source;
    }

    public SoundBuilder setSource(Sound.Source source) {
        this.source = source;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public SoundBuilder setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public float getPitch() {
        return pitch;
    }

    public SoundBuilder setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    @Nullable
    public Key asKey(){
        if(key == null)
            return null;

        if(StringUtils.isBlank(subKey))
            return Key.key(key);
        else return Key.key(key, subKey);
    }

    @Override
    @Nullable
    public Sound build() {
        Key key = asKey();
        if(key == null)
            return null;

        if(pitch < 0 || pitch > 2)
            pitch = 1f;

        return Sound.sound(key, source, radius, pitch);
    }

    @Override
    public Class<Sound> ofClazz() {
        return Sound.class;
    }

    @Override
    public SoundBuilder clear() {
        this.key = null;
        this.source = null;

        this.radius = 1.0f;
        this.pitch = 1.0f;
        return this;
    }
}
