package io.github.alenalex.adventurelib.velocity.impl;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import io.github.alenalex.adventurelib.common.interfaces.Messenger;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class VelocityMessenger implements Messenger<Player> {

    public static VelocityMessengerBuilder builder(){
        return new VelocityMessengerBuilder();
    }

    private final ProxyServer plugin;
    private final Translator defaultTranslator;

    public VelocityMessenger(ProxyServer plugin, Translator defaultTranslator) {
        this.plugin = plugin;
        this.defaultTranslator = defaultTranslator;
    }

    @Override
    public void closeMessenger() {

    }

    @Override
    public void sendMessage(Player player, String message) {
        player.sendMessage(defaultTranslator.colorize(message));
    }

    @Override
    public void sendMessage(Player player, String message, Translator translatorToUse) {
        player.sendMessage(player, translatorToUse.colorize(message));
    }

    @Override
    public void sendMessage(Player player, Component message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessageLater(Player player, int delayInSecs, String message) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                sendMessage(player, message);
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessageLater(Player player, int delayInSecs, Component message) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                sendMessage(player, message);
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Player player, int delayInSecs, String... messages) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(String eachMessage : messages){
                    sendMessage(player, eachMessage);
                }
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Player player, int delayInSecs, Component... messages) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Component eachMessage : messages){
                    sendMessage(player, eachMessage);
                }
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, String message) {
        for(Player player : players){
            sendMessage(player, message);
        }
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, Component message) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, message);
                }
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, String... messages) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, messages);
                }
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, Component... messages) {
        plugin.getScheduler().buildTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, messages);
                }
            }
        }).delay(delayInSecs, TimeUnit.SECONDS);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        player.sendActionBar(defaultTranslator.colorize(message));
    }

    @Override
    public void sendActionBar(Player player, String message, Translator translatorToUse) {
        player.sendActionBar(translatorToUse.colorize(message));

    }

    @Override
    public void sendActionBar(Player player, Component message) {
        player.sendActionBar(message);
    }

    @Override
    public void showTitle(Player player, @NotNull Title title) {
        player.showTitle(title);
    }

    @Override
    public void showBossBar(Player player, @NotNull BossBar bossBar) {
        player.showBossBar(bossBar);
    }

    @Override
    public Audience asAudienceOf(Player player) {
        return player;
    }

    @Override
    public void playSound(Player player, Sound sound, Sound.Emitter emitter) {
        player.playSound(sound, emitter);
    }

    @Override
    public void playSound(Player player, Sound sound) {
        player.playSound(sound);
    }

    @Override
    public void openBook(Player player, Book book) {
        player.openBook(book);
    }

    @Override
    public List<Audience> asAudienceWithPerms(@NotNull String perms) {
        return null;
    }

    @Override
    public List<Audience> asAudienceOf(Predicate<Player> condition) {
        return null;
    }

    @Override
    public void sendConsoleMessage(String text) {
        plugin.getConsoleCommandSource().sendMessage(defaultTranslator.colorize(text));
    }

    @Override
    public void sendConsoleMessage(Component text) {
        plugin.getConsoleCommandSource().sendMessage(text);
    }

    @Override
    public void broadcast(String text) {
        for(Player player : plugin.getAllPlayers()){
            player.sendMessage(defaultTranslator.colorize(text));
        }
    }

    @Override
    public void broadcast(Component text) {
        for(Player player : plugin.getAllPlayers()){
            player.sendMessage(text);
        }
    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull String text) {

    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull String text) {

    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull String text, Translator translator) {

    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull Component text) {

    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull Component text) {

    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull String text, Translator translator) {

    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull String header, @NotNull String footer) {

    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull String header, @NotNull String footer, Translator translator) {

    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull Component header, @NotNull Component footer) {

    }
}
