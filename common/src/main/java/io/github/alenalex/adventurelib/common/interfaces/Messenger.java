package io.github.alenalex.adventurelib.common.interfaces;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface Messenger<T> {

    void closeMessenger();

    /**
     * Send a String message to player. The message will be converted using the default translator
     * @param player Who to send the message
     * @param message Message that need to be sent
     */
    void sendMessage(T player, String message);

    /**
     * Send a String message to player. The message will be converted using the specified translator
     * @param player Who to send the message
     * @param message Message that need to be sent
     * @param translatorToUse Translator to use
     */
    void sendMessage(T player, String message, Translator translatorToUse);

    /**
     * Send a Component message to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param message Message that need to be sent
     */
    void sendMessage(T player, Component message);

    /**
     * Send multiple {@link String} messages to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param messages Message that need to be sent
     */
    default void sendMessage(T player, String... messages){
        for(String eachMessage : messages){
            sendMessage(player, eachMessage);
        }
    }

    /**
     * Send multiple {@link String} messages to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param messages Message that need to be sent
     * @param translatorToUse Translator to use
     */
    default void sendMessage(T player, Translator translatorToUse, String... messages){
        for(String eachMessage : messages){
            sendMessage(player, eachMessage, translatorToUse);
        }
    }

    /**
     * Send multiple {@link Component} messages to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param messages Message that need to be sent
     */
    default void sendMessage(T player, Component... messages){
        for(Component eachMessage : messages){
            sendMessage(player, eachMessage);
        }
    }

    /**
     * Send a {@link List<String>} messages to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param messages Message that need to be sent
     */
    default void sendStringList(T player, Collection<String> messages){
        for(String eachMessage : messages){
            sendMessage(player, eachMessage);
        }
    }

    default void sendStringList(T player, Translator translatorToUse , Collection<String> messages){
        for(String eachMessage : messages){
            sendMessage(player, eachMessage, translatorToUse);
        }
    }

    /**
     * Send a {@link List<Component>} messages to player. The message will be converted using the specified serialized
     * @param player Who to send the message
     * @param messages Message that need to be sent
     */
    default void sendComponentList(T player, Collection<Component> messages){
        for(Component eachMessage : messages){
            sendMessage(player, eachMessage);
        }
    }

    /**
     * Send a String message to multiple players
     * @param players List of Players to whom message needs to be sent
     * @param message Message that need to be sent
     */
    default void sendMessageToPlayers(Collection<T> players, String message){
        for(T player : players){
            sendMessage(player, message);
        }
    }

    default void sendMessageToPlayers(Collection<T> players, String message, Translator translatorToUse){
        for(T player : players){
            sendMessage(player, message, translatorToUse);
        }
    }

    /**
     * Send a Component message to multiple players
     * @param players List of Players to whom message needs to be sent
     * @param message Message that need to be sent
     */
    default void sendMessageToPlayers(Collection<T> players, Component message){
        for(T player : players){
            sendMessage(player, message);
        }
    }

    /**
     * Send multiple message to multiple players
     * @param players List of Players to whom message needs to be sent
     * @param messages Messages that need to be sent
     */
    default void sendMessageToPlayers(Collection<T> players, String... messages){
        for(String eachMessage : messages){
            sendMessageToPlayers(players, eachMessage);
        }
    }

    default void sendMessageToPlayers(Collection<T> players, Translator translatorToUse ,String... messages){
        for(String eachMessage : messages){
            sendMessageToPlayers(players, eachMessage, translatorToUse);
        }
    }

    /**
     * Send multiple message to multiple players
     * @param players List of Players to whom message needs to be sent
     * @param messages Messages that need to be sent
     */
    default void sendMessagesToPlayers(Collection<T> players, Component... messages){
        for(Component eachMessage : messages){
            sendMessageToPlayers(players, eachMessage);
        }
    }

    /**
     * Sends a String message after X secs
     * @param player Who to send the message
     * @param message Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessageLater(T player, int delayInSecs, String message);

    /**
     * Sends a Component message after X secs
     * @param player Who to send the message
     * @param message Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessageLater(T player, int delayInSecs, Component message);

    /**
     * Sends multiple String message after X secs
     * @param player Who to send the message
     * @param messages Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(T player, int delayInSecs, String... messages);

    /**
     * Sends multiple Component message after X secs
     * @param player Who to send the message
     * @param messages Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(T player, int delayInSecs, Component... messages);

    /**
     * Sends message to multiple players after X sec
     * @param players Who to send the message
     * @param message Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(Collection<T> players, int delayInSecs, String message);

    /**
     * Sends component message to multiple players after X sec
     * @param players Who to send the message
     * @param message Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(Collection<T> players, int delayInSecs, Component message);

    /**
     * Sends multiple String message to multiple players after X sec
     * @param players Who to send the message
     * @param messages Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(Collection<T> players, int delayInSecs, String... messages);

    /**
     * Sends multiple Component message to multiple players after X sec
     * @param players Who to send the message
     * @param messages Message that need to be sent
     * @param delayInSecs delay in which the message needed to be sent
     */
    void sendMessagesLater(Collection<T> players, int delayInSecs, Component... messages);

    /**
     * Sends a String actionbar message to player
     * @param player Who to send the Actionbar
     * @param message Message that need to be sent
     */
    void sendActionBar(T player, String message);

    /**
     * Sends a String actionbar message to player
     * @param player Who to send the Actionbar
     * @param message Message that need to be sent
     * @param translatorToUse Translator to use
     */
    void sendActionBar(T player, String message, Translator translatorToUse);

    /**
     * Sends a Component actionbar message to player
     * @param player Who to send the Actionbar
     * @param message Message that need to be sent
     */
    void sendActionBar(T player, Component message);

    default void sendActionBar(Collection<T> players, String message){
        for(T player : players){
            sendActionBar(player, message);
        }
    }

    /**
     * Send action bar to players
     * @param players
     * @param message
     */
    default void sendActionBar(Collection<T> players, Component message){
        for(T player : players){
            sendActionBar(player, message);
        }
    }

    /**
     * Show title to players
     * @param player
     * @param title
     */
    void showTitle(T player, @NotNull Title title);

    /**
     * Shows title to a collection of players
     * @param players
     * @param title
     */
    default void showTitle(Collection<T> players, @NotNull Title title){
        for(T player : players){
            showTitle(players, title);
        }
    }


    void showBossBar(T player, @NotNull BossBar bossBar);

    default void showBossBar(Collection<T> players, @NotNull BossBar bossBar){
        for(T player : players){
            showBossBar(players, bossBar);
        }
    }

    Audience asAudienceOf(T player);

    default List<Audience> asAudienceOf(T... players){
        final List<Audience> audiences = new ArrayList<>();
        for(T player : players){
            audiences.add(asAudienceOf(player));
        }
        return audiences;
    }

    void playSound(T player, Sound sound, Sound.Emitter emitter);

    void playSound(T player, Sound sound);

    void openBook(T player, Book book);

    default void openBook(Collection<T> players, Book book){
        for(T player : players){
            openBook(player, book);
        }
    }

    List<Audience> asAudienceWithPerms(@NotNull String perms);

    List<Audience> asAudienceOf(Predicate<T> condition);

    void sendConsoleMessage(String text);

    void sendConsoleMessage(Component text);

    void broadcast(String text);

    void broadcast(Component text);

    void setPlayerListHeader(T player, @NotNull String text);

    void setPlayerListFooter(T player, @NotNull String text);

    void setPlayerListHeader(T player, @NotNull String text, Translator translator);

    void setPlayerListHeader(T player, @NotNull Component text);

    void setPlayerListFooter(T player, @NotNull Component text);

    void setPlayerListFooter(T player, @NotNull String text, Translator translator);

    void setPlayerListHeaderAndFooter(T player, @NotNull String header, @NotNull String footer);

    void setPlayerListHeaderAndFooter(T player, @NotNull String header, @NotNull String footer, Translator translator);

    void setPlayerListHeaderAndFooter(T player, @NotNull Component header, @NotNull Component footer);


}
