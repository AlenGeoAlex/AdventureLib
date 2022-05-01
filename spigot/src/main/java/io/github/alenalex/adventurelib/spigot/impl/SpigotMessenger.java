package io.github.alenalex.adventurelib.spigot.impl;

import io.github.alenalex.adventurelib.common.interfaces.Messenger;
import io.github.alenalex.adventurelib.common.interfaces.Translator;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class SpigotMessenger implements Messenger<Player> {

    private final JavaPlugin plugin;
    private final Translator translator;
    private final BukkitAudiences audiences;

    public SpigotMessenger(JavaPlugin plugin, Translator translator ) {
        this.plugin = plugin;
        this.translator = translator;
        this.audiences = BukkitAudiences.create(plugin);
    }

    @Override
    public void closeMessenger() {
        this.audiences.close();
    }

    @Override
    public void sendMessage(Player player, String message) {
        this.audiences.player(player).sendMessage(translator.colorize(message));
    }

    @Override
    public void sendMessage(Player player, String message, Translator translatorToUse) {
        this.audiences.player(player).sendMessage(translatorToUse.colorize(message));
    }

    @Override
    public void sendMessage(Player player, Component message) {
        this.audiences.player(player).sendMessage(message);
    }

    @Override
    public void sendMessageLater(Player player, int delayInSecs, String message) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                sendMessage(player, message);
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessageLater(Player player, int delayInSecs, Component message) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                sendMessage(player, message);
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Player player, int delayInSecs, String... messages) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(String eachMessage : messages) {
                    sendMessage(player, eachMessage);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Player player, int delayInSecs, Component... messages) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Component eachMessage : messages) {
                    sendMessage(player, eachMessage);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, String message) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, message);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, Component message) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, message);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, String... messages) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, messages);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendMessagesLater(Collection<Player> players, int delayInSecs, Component... messages) {
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : players){
                    sendMessage(player, messages);
                }
            }
        }, 20L * delayInSecs);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        this.audiences.player(player).sendMessage(translator.colorize(message));
    }

    @Override
    public void sendActionBar(Player player, String message, Translator translatorToUse) {
        this.audiences.player(player).sendMessage(translatorToUse.colorize(message));
    }

    @Override
    public void sendActionBar(Player player, Component message) {
        this.audiences.player(player).sendActionBar(message);
    }

    @Override
    public void showTitle(Player player, @NotNull Title title) {
        this.audiences.player(player).showTitle(title);
    }

    @Override
    public void showBossBar(Player player, @NotNull BossBar bossBar) {
        this.audiences.player(player).showBossBar(bossBar);
    }

    @Override
    public Audience asAudienceOf(Player player) {
        return this.audiences.player(player);
    }

    @Override
    public void playSound(Player player, Sound sound, Sound.Emitter emitter) {
        this.audiences.player(player).playSound(sound, emitter);
    }

    @Override
    public void playSound(Player player, Sound sound) {
        this.audiences.player(player).playSound(sound);
    }

    @Override
    public void openBook(Player player, Book book) {
        this.audiences.player(player).openBook(book);
    }

    @Override
    public List<Audience> asAudienceWithPerms(@NotNull String perms) {
        final List<Audience> audienceList = new ArrayList<>();
        for(Player player : plugin.getServer().getOnlinePlayers()){
            if(!player.hasPermission(perms))
                continue;

            audienceList.add(audiences.player(player));
        }
        return audienceList;
    }

    @Override
    public List<Audience> asAudienceOf(Predicate<Player> condition) {
        final List<Audience> audienceList = new ArrayList<>();
        for(Player player : plugin.getServer().getOnlinePlayers()){
            if(!condition.test(player))
                continue;

            audienceList.add(audiences.player(player));
        }
        return audienceList;
    }

    @Override
    public void sendConsoleMessage(String text) {
        this.audiences.console().sendMessage(translator.colorize(text));
    }

    @Override
    public void sendConsoleMessage(Component text) {
        this.audiences.console().sendMessage(text);
    }

    @Override
    public void broadcast(String text) {
        this.audiences.all().sendMessage(translator.colorize(text));
    }

    @Override
    public void broadcast(Component text) {
        this.audiences.all().sendMessage(text);
    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull String text) {
        this.audiences.player(player).sendPlayerListHeader(translator.colorize(text));
    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull String text) {
        this.audiences.player(player).sendPlayerListFooter(translator.colorize(text));
    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull String text, Translator translatorToUse) {
        this.audiences.player(player).sendPlayerListHeader(translatorToUse.colorize(text));
    }

    @Override
    public void setPlayerListHeader(Player player, @NotNull Component text) {
        this.audiences.player(player).sendPlayerListHeader(text);
    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull Component text) {
        this.audiences.player(player).sendPlayerListFooter(text);
    }

    @Override
    public void setPlayerListFooter(Player player, @NotNull String text, Translator translatorToUse) {
        this.audiences.player(player).sendPlayerListFooter(translatorToUse.colorize(text));
    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull String header, @NotNull String footer) {
        this.audiences.player(player).sendPlayerListHeaderAndFooter(translator.colorize(header), translator.colorize(footer));
    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull String header, @NotNull String footer, Translator translatorToUse) {
        this.audiences.player(player).sendPlayerListHeaderAndFooter(translatorToUse.colorize(header), translatorToUse.colorize(footer));
    }

    @Override
    public void setPlayerListHeaderAndFooter(Player player, @NotNull Component header, @NotNull Component footer) {
        this.audiences.player(player).sendPlayerListHeaderAndFooter(header, footer);
    }
}
