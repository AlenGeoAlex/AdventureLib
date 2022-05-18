package io.github.alenalex.adventurelib.common.builder;

import io.github.alenalex.adventurelib.common.interfaces.ComponentBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translatable;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BossBarBuilder implements ComponentBuilder<BossBar>, Translatable<BossBarBuilder> {

    private Translator defaultTranslator = Translator.MINI_MESSAGE;

    public static BossBarBuilder builder(){
        return new BossBarBuilder();
    }

    private String text;
    private float progressBar;
    private BossBar.Color bossBarColor;
    private BossBar.Overlay bossBarOverLay;
    private final Set<BossBar.Flag> flagRegister;

    private BossBarBuilder() {
        this.text = null;
        this.progressBar = 1.0f;
        this.bossBarColor = BossBar.Color.RED;
        this.bossBarOverLay = BossBar.Overlay.NOTCHED_20;
        this.flagRegister = new HashSet<>();
    }

    public String getText() {
        return text;
    }

    public BossBarBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public float getProgressBar() {
        return progressBar;
    }

    public BossBarBuilder setProgressBar(float progressBar) {
        this.progressBar = progressBar;
        return this;
    }

    public BossBar.Color getBossBarColor() {
        return bossBarColor;
    }

    public BossBarBuilder setBossBarColor(BossBar.Color bossBarColor) {
        this.bossBarColor = bossBarColor;
        return this;
    }

    public BossBar.Overlay getBossBarOverLay() {
        return bossBarOverLay;
    }

    public BossBarBuilder setBossBarOverLay(BossBar.Overlay bossBarOverLay) {
        this.bossBarOverLay = bossBarOverLay;
        return this;
    }

    public Collection<BossBar.Flag> getBossBarFlags() {
        return flagRegister;
    }

    public BossBarBuilder registerFlagFor(BossBar.Flag flag){
        this.flagRegister.add(flag);
        return this;
    }

    public boolean isFlagRegistered(BossBar.Flag flag){
        return this.flagRegister.contains(flag);
    }

    public BossBarBuilder unregisterFlagFor(BossBar.Flag flag){
        this.flagRegister.remove(flag);
        return this;
    }

    @Override
    public BossBar build() {
        final Component bossBarText = StringUtils.isBlank(this.text) ? Component.empty() : defaultTranslator.colorize(this.text);
        return BossBar.bossBar(bossBarText, progressBar, bossBarColor, bossBarOverLay, flagRegister);
    }

    @Override
    public BossBarBuilder resetBuilder() {
        this.text = null;
        this.progressBar = 1.0f;
        this.bossBarColor = BossBar.Color.RED;
        this.bossBarOverLay = BossBar.Overlay.NOTCHED_20;
        this.flagRegister.clear();
        return this;
    }

    @Override
    public Class<BossBar> ofClazz() {
        return BossBar.class;
    }

    @Override
    public BossBarBuilder withLegacyTranslator() {
        this.defaultTranslator = Translator.LEGACY;
        return this;
    }

    @Override
    public BossBarBuilder withMiniMessageTranslator() {
        this.defaultTranslator = Translator.MINI_MESSAGE;
        return this;
    }

    @Override
    protected BossBarBuilder clone() throws CloneNotSupportedException {
        return (BossBarBuilder) super.clone();
    }
}
