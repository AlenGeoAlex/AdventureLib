package io.github.alenalex.adventurelib.bungee.impl;

import io.github.alenalex.adventurelib.common.interfaces.Messenger;
import io.github.alenalex.adventurelib.common.interfaces.MessengerBuilder;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class BungeeMessenger implements Messenger<ProxiedPlayer> {

    public static MessengerBuilder<Plugin> builder(){
        return new BungeeMessengerBuilder();
    }

    private final Plugin plugin;
    private final Translator defaultTranslator;
    private final BungeeAudiences audiences;

    public BungeeMessenger(Plugin plugin, Translator defaultTranslator) {
        this.plugin = plugin;
        this.defaultTranslator = defaultTranslator;
        this.audiences = BungeeAudiences.create(plugin);
    }

    @Override
    public void closeMessenger() {
        this.audiences.close();
    }

    @Override
    public void sendMessage(ProxiedPlayer player, String message) {
        audiences.player(player).sendMessage(defaultTranslator.colorize(message));
    }

    @Override
    public void sendMessage(ProxiedPlayer player, String message, Translator translatorToUse) {
        audiences.player(player).sendMessage(translatorToUse.colorize(message));
    }

    @Override
    public void sendMessage(ProxiedPlayer player, Component message) {
        audiences.player(player).sendMessage(message);
    }

    @Override
    public void sendMessageLater(ProxiedPlayer player, int delayInSecs, String message) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(player).sendMessage(defaultTranslator.colorize(message));
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessageLater(ProxiedPlayer player, int delayInSecs, Component message) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(player).sendMessage(message);
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(ProxiedPlayer player, int delayInSecs, String... messages) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(String eachMessage : messages){
                    audiences.player(player).sendMessage(defaultTranslator.colorize(eachMessage));
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(ProxiedPlayer player, int delayInSecs, Component... messages) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Component eachMessage : messages){
                    audiences.player(player).sendMessage(eachMessage);
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<ProxiedPlayer> players, int delayInSecs, String message) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(ProxiedPlayer player : players){
                    audiences.player(player).sendMessage(defaultTranslator.colorize(message));
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<ProxiedPlayer> players, int delayInSecs, Component message) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(ProxiedPlayer player : players){
                    audiences.player(player).sendMessage(message);
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<ProxiedPlayer> players, int delayInSecs, String... messages) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(ProxiedPlayer player : players){
                    for(String eachMessage : messages){
                        audiences.player(player).sendMessage(defaultTranslator.colorize(eachMessage));
                    }
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<ProxiedPlayer> players, int delayInSecs, Component... messages) {
        plugin.getProxy().getScheduler().schedule(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(ProxiedPlayer player : players){
                    for(Component eachMessage : messages){
                        audiences.player(player).sendMessage(eachMessage);
                    }
                }
            }
        }, delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendActionBar(ProxiedPlayer player, String message) {
        audiences.player(player).sendActionBar(defaultTranslator.colorize(message));
    }

    @Override
    public void sendActionBar(ProxiedPlayer player, String message, Translator translatorToUse) {
        audiences.player(player).sendActionBar(translatorToUse.colorize(message));
    }

    @Override
    public void sendActionBar(ProxiedPlayer player, Component message) {
        audiences.player(player).sendActionBar(message);
    }

    @Override
    public void showTitle(ProxiedPlayer player, @NotNull Title title) {
        audiences.player(player).showTitle(title);
    }

    @Override
    public void showBossBar(ProxiedPlayer player, @NotNull BossBar bossBar) {
        this.audiences.player(player).showBossBar(bossBar);
    }

    @Override
    public Audience asAudienceOf(ProxiedPlayer player) {
        return audiences.player(player);
    }

    @Override
    public void playSound(ProxiedPlayer player, Sound sound, Sound.Emitter emitter) {
        audiences.player(player).playSound(sound, emitter);
    }

    @Override
    public void playSound(ProxiedPlayer player, Sound sound) {
        audiences.player(player).playSound(sound);
    }

    @Override
    public void openBook(ProxiedPlayer player, Book book) {
        this.audiences.player(player).openBook(book);
    }

    @Override
    public void hideBossBar(@NotNull ProxiedPlayer player, @NotNull BossBar bossBar) {
        this.audiences.player(player).hideBossBar(bossBar);
    }

    @Override
    public List<Audience> asAudienceWithPerms(@NotNull String perms) {
        final List<Audience> audienceList = new ArrayList<>();
        for(ProxiedPlayer player : plugin.getProxy().getPlayers()){
            if(player.hasPermission(perms))
                audienceList.add(asAudienceOf(player));
        }
        return audienceList;
    }




    @Override
    public List<Audience> asAudienceOf(Predicate<ProxiedPlayer> condition) {
        final List<Audience> audienceList = new ArrayList<>();
        for(ProxiedPlayer player : plugin.getProxy().getPlayers()){
            if(condition.test(player))
                audienceList.add(asAudienceOf(player));
        }
        return audienceList;
    }

    @Override
    public void sendConsoleMessage(String text) {
        audiences.console().sendMessage(defaultTranslator.colorize(text));
    }

    @Override
    public void sendConsoleMessage(Component text) {
        audiences.console().sendMessage(text);
    }

    @Override
    public void broadcast(String text) {
        audiences.all().sendMessage(defaultTranslator.colorize(text));
    }

    @Override
    public void broadcast(Component text) {
        audiences.all().sendMessage(text);
    }

    @Override
    public void setPlayerListHeader(ProxiedPlayer player, @NotNull String text) {
        audiences.player(player).sendPlayerListHeader(defaultTranslator.colorize(text));
    }

    @Override
    public void setPlayerListFooter(ProxiedPlayer player, @NotNull String text) {
        audiences.player(player).sendPlayerListFooter(defaultTranslator.colorize(text));

    }

    @Override
    public void setPlayerListHeader(ProxiedPlayer player, @NotNull String text, Translator translator) {
        audiences.player(player).sendPlayerListHeader(translator.colorize(text));
    }

    @Override
    public void setPlayerListHeader(ProxiedPlayer player, @NotNull Component text) {
        audiences.player(player).sendPlayerListHeader(text);
    }

    @Override
    public void setPlayerListFooter(ProxiedPlayer player, @NotNull Component text) {
        audiences.player(player).sendPlayerListFooter(text);
    }

    @Override
    public void setPlayerListFooter(ProxiedPlayer player, @NotNull String text, Translator translator) {
        audiences.player(player).sendPlayerListFooter(translator.colorize(text));
    }

    @Override
    public void setPlayerListHeaderAndFooter(ProxiedPlayer player, @NotNull String header, @NotNull String footer) {
        audiences.player(player).sendPlayerListHeaderAndFooter(defaultTranslator.colorize(header), defaultTranslator.colorize(footer));
    }

    @Override
    public void setPlayerListHeaderAndFooter(ProxiedPlayer player, @NotNull String header, @NotNull String footer, Translator translator) {
        audiences.player(player).sendPlayerListHeaderAndFooter(translator.colorize(header), translator.colorize(footer));
    }

    @Override
    public void setPlayerListHeaderAndFooter(ProxiedPlayer player, @NotNull Component header, @NotNull Component footer) {
        audiences.player(player).sendPlayerListHeaderAndFooter(header, footer);
    }

    public Audience audiencesOfServer(@NotNull String name){
        return audiences.server(name);
    }
}
