package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.eld.bukkit.ELDLifeCycle;
import com.ericlam.mc.minigames.core.main.MinigamesCore;
import com.ericlam.mc.minigames.core.registable.Registration;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Queue;

public final class MinigamesBridgeLifeCycle implements ELDLifeCycle {


    @Named("later-registers")
    @Inject
    private Queue<LaterRegistration> laterRegistrations;

    @Inject
    private Injector injector;


    @Override
    public void onEnable(JavaPlugin javaPlugin) {
        Registration registration = MinigamesCore.getRegistration();
        javaPlugin.getLogger().info("Registering all minigame registrations...");
        while (!laterRegistrations.isEmpty()) {
            LaterRegistration laterRegistration = laterRegistrations.poll();
            laterRegistration.registerLater(registration, injector);
        }
        javaPlugin.getLogger().info("Registration Completed.");
    }

    @Override
    public void onDisable(JavaPlugin javaPlugin) {

    }
}
