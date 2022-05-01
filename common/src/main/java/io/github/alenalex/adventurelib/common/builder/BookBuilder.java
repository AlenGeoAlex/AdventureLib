package io.github.alenalex.adventurelib.common.builder;

import io.github.alenalex.adventurelib.common.interfaces.ComponentBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translatable;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class BookBuilder implements ComponentBuilder<Book>, Translatable<BookBuilder> {

    private Translator defaultTranslator = Translator.MINI_MESSAGE;

    private String bookTitle;
    private String bookAuthor;
    private final Collection<Component> bookPages;

    private BookBuilder() {
        this.bookAuthor = null;
        this.bookTitle = null;
        this.bookPages = new ArrayList<>();
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookBuilder setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public BookBuilder setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
        return this;
    }

    public Collection<Component> getBookPages() {
        return bookPages;
    }

    public BookBuilder addEmptyPage(){
        this.bookPages.add(Component.empty());
        return this;
    }

    public BookBuilder addPage(String text){
        if(StringUtils.isBlank(text))
            addEmptyPage();
        else this.bookPages.add(defaultTranslator.colorize(text));
        return this;
    }

    public BookBuilder addPage(Component text){
        this.bookPages.add(text);
        return this;
    }

    public BookBuilder addPages(Collection<String> pages){
        this.bookPages.addAll(
                pages.stream()
                        .map(stringText -> defaultTranslator.colorize(stringText))
                        .collect(Collectors.toList())
        );
        return this;
    }

    public BookBuilder addComponentPages(Collection<Component> pages){
        this.bookPages.addAll(pages);
        return this;
    }

    public int currentPageSize(){
        return this.bookPages.size();
    }

    @Override
    public Book build() {
        final Component titleComponent = StringUtils.isBlank(this.bookTitle) ? Component.empty() : defaultTranslator.colorize(this.bookTitle);
        final Component authorComponent = StringUtils.isBlank(this.bookAuthor) ? Component.empty() : defaultTranslator.colorize(this.bookAuthor);
        return Book.book(titleComponent, authorComponent, bookPages);
    }

    @Override
    public BookBuilder clear() {
        this.bookAuthor = null;
        this.bookTitle = null;
        this.bookPages.clear();
        return this;
    }

    @Override
    public Class<Book> ofClazz() {
        return Book.class;
    }

    @Override
    public BookBuilder withLegacyTranslator() {
        this.defaultTranslator = Translator.LEGACY;
        return this;
    }

    @Override
    public BookBuilder withMiniMessageTranslator() {
        this.defaultTranslator = Translator.MINI_MESSAGE;
        return this;
    }
}
