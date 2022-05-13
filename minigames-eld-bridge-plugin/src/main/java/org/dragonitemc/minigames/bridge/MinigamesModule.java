package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.minigames.core.MinigamesAPI;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.manager.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public final class MinigamesModule extends AbstractModule {

    private final Map<Class<?>, Provider<?>> apiProvider = new ConcurrentHashMap<>();

    private final ELDMinigameInstallation install;

    public MinigamesModule(ELDMinigameInstallation install) {
        this.install = install;

        registerAPIProvider(MinigamesAPI.class, MinigamesCore::getApi);
        registerAPIProvider(ArenaCreateManager.class, () -> MinigamesCore.getApi().getArenaCreateManager());
        registerAPIProvider(ArenaManager.class, () -> MinigamesCore.getApi().getArenaManager());
        registerAPIProvider(FireWorkManager.class, () -> MinigamesCore.getApi().getFireWorkManager());
        registerAPIProvider(GameItemManager.class, () -> MinigamesCore.getApi().getGameItemManager());
        registerAPIProvider(GameManager.class, () -> MinigamesCore.getApi().getGameManager());
        registerAPIProvider(GameStatsManager.class, () -> MinigamesCore.getApi().getGameStatsManager());
        registerAPIProvider(GameUtils.class, () -> MinigamesCore.getApi().getGameUtils());
        registerAPIProvider(LobbyManager.class, () -> MinigamesCore.getApi().getLobbyManager());
        registerAPIProvider(PlayerManager.class, () -> MinigamesCore.getApi().getPlayerManager());
        registerAPIProvider(ScheduleManager.class, () -> MinigamesCore.getApi().getScheduleManager());

    }

    @Override
    protected void configure() {
        getAPIProviders().forEach((k,v) -> bind(k).toProvider(v));
        bind(new TypeLiteral<Queue<LaterRegistration>>(){}).annotatedWith(Names.named("later-registers")).toInstance(install.getRegistrationQueue());
    }


    public <T> void registerAPIProvider(Class<T> api, Provider<T> provider) {
        apiProvider.put(api, provider);
    }

    @SuppressWarnings("unchecked")
    private <T> Map<Class<T>, Provider<T>> getAPIProviders(){
        var map = new HashMap<Class<T>, Provider<T>>();
        this.apiProvider.forEach((k,v) -> map.put((Class<T>)k, (Provider<T>)v));
        return map;
    }
}
