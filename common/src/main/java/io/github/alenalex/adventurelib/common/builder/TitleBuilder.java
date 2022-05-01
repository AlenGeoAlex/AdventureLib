package io.github.alenalex.adventurelib.common.builder;

import io.github.alenalex.adventurelib.common.interfaces.ComponentBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translatable;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

public class TitleBuilder implements ComponentBuilder<Title>, Translatable<TitleBuilder> {

    public static TitleBuilder builder(){
        return new TitleBuilder();
    }

    private Translator defaultTranslator = Translator.MINI_MESSAGE;


    private String title;
    private String subTitle;
    private long fadeIn;
    private long stayOn;
    private long fadeOut;

    private TitleBuilder(){
        this.title = null;
        this.subTitle = null;
        this.fadeIn = 500;
        this.fadeOut = 500;
        this.stayOn = 2000;
    }

    public TitleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TitleBuilder setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public TitleBuilder setFadeIn(long fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public TitleBuilder setStayOn(long stayOn) {
        this.stayOn = stayOn;
        return this;
    }

    public TitleBuilder setFadeOut(long fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public long getFadeIn() {
        return fadeIn;
    }

    public long getStayOn() {
        return stayOn;
    }

    public long getFadeOut() {
        return fadeOut;
    }

    @Override
    public Title build() {
        final Component titleComponent = StringUtils.isBlank(this.title) ? Component.empty() : this.defaultTranslator.colorize(this.title);
        final Component subTitleComponent = StringUtils.isBlank(this.subTitle) ? Component.empty() : this.defaultTranslator.colorize(this.subTitle);
        final Title.Times timeComponent = Title.Times.times(Duration.ofMillis(this.fadeIn), Duration.ofMillis(this.stayOn), Duration.ofMillis(this.fadeOut));
        return Title.title(titleComponent, subTitleComponent, timeComponent);
    }

    @Override
    public TitleBuilder clear() {
        this.title = null;
        this.subTitle = null;
        this.fadeIn = 500;
        this.fadeOut = 500;
        this.stayOn = 2000;
        return this;
    }

    @Override
    public Class<Title> ofClazz() {
        return Title.class;
    }

    @Override
    public TitleBuilder withLegacyTranslator() {
        this.defaultTranslator = Translator.LEGACY;
        return this;
    }

    @Override
    public TitleBuilder withMiniMessageTranslator() {
        this.defaultTranslator = Translator.MINI_MESSAGE;
        return this;
    }
}
