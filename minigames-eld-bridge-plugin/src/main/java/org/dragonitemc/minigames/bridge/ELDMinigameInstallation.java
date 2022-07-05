package org.dragonitemc.minigames.bridge;

import com.dragonite.mc.dnmc.core.managers.builder.AbstractInventoryBuilder;
import com.dragonite.mc.dnmc.core.misc.commands.DefaultCommand;
import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.character.GamePlayerHandler;
import com.ericlam.mc.minigames.core.game.GameTeam;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.gamestats.GameStatsHandler;
import com.ericlam.mc.minigames.core.registable.Voluntary;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public final class ELDMinigameInstallation implements MinigameInstallation {

    private final Queue<LaterRegistration> registrations = new LinkedList<>();

    private final ELDCompulsory compulsory = new ELDCompulsory();
    private final ELDVoluntary voluntary = new ELDVoluntary();


    @Override
    public Compulsory getCompulsory() {
        return compulsory;
    }

    @Override
    public Voluntary getVoluntary() {
        return voluntary;
    }

    public Queue<LaterRegistration> getRegistrationQueue() {
        return registrations;
    }

    private class ELDCompulsory implements Compulsory {

        @Override
        public void registerArenaCommand(DefaultCommand defaultCommand, JavaPlugin plugin) {
            registrations.offer((api, injector) -> {
                injector.injectMembers(defaultCommand);
                api.getCompulsory().registerArenaCommand(defaultCommand, plugin);
            });
        }

        @Override
        public void registerArenaConfig(Class<? extends ArenaConfig> arenaConfig) {
            registrations.offer((api, injector) -> {
               var arenaConfigInstance = injector.getInstance(arenaConfig);
               api.getCompulsory().registerArenaConfig(arenaConfigInstance);
            });
        }

        @Override
        public void registerArenaMechanic(Class<? extends ArenaMechanic> arenaMechanic) {
            registrations.offer((api, injector) -> {
                var arenaMechanicInstance = injector.getInstance(arenaMechanic);
                api.getCompulsory().registerArenaMechanic(arenaMechanicInstance);
            });
        }

        @Override
        public void registerEndTask(Class<? extends SectionTask> task) {
            registrations.offer((api, injector) -> {
                var taskInstance = injector.getInstance(task);
                api.getCompulsory().registerEndTask(taskInstance);
            });
        }

        @Override
        public void registerGamePlayerHandler(Class<? extends GamePlayerHandler> gamePlayerHandler) {
            registrations.offer((api, injector) -> {
               var gamePlayerHandlerInstance = injector.getInstance(gamePlayerHandler);
               api.getCompulsory().registerGamePlayerHandler(gamePlayerHandlerInstance);
            });
        }

        @Override
        public void registerGameStatsHandler(Class<? extends GameStatsHandler> gameStatsHandler) {
            registrations.offer(((api, injector) -> {
                var gameStatsHandlerInstance = injector.getInstance(gameStatsHandler);
                api.getCompulsory().registerGameStatsHandler(gameStatsHandlerInstance);
            }));
        }

        @Override
        public void registerLobbyTask(Class<? extends SectionTask> task) {
            registrations.offer((api, injector) -> {
                var taskInstance = injector.getInstance(task);
                api.getCompulsory().registerLobbyTask(taskInstance);
            });
        }

        @Override
        public void registerVoteGUI(AbstractInventoryBuilder builder, Integer... slots) {
            registrations.offer((api, injector) -> api.getCompulsory().registerVoteGUI(builder, slots));
        }
    }


    private class ELDVoluntary implements Voluntary {

        @Override
        public void registerGameTask(Class<? extends InGameState> state, Class<? extends SectionTask> task) {
            registrations.offer((api, injector) -> {
               var stateInstance = injector.getInstance(state);
               var taskInstance = injector.getInstance(task);
               api.getVoluntary().registerGameTask(stateInstance, taskInstance);
            });
        }

        @Override
        public void registerGameItem(Class<? extends Consumer<GameItemRegister>> register) {
            registrations.offer((api, injector) -> {
                var registerInstance = injector.getInstance(register);
                registerInstance.accept(new ELDGameItemRegister(api.getVoluntary()));
            });
        }
    }
}
