package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.minigames.core.registable.Registration;
import com.google.inject.Injector;

@FunctionalInterface
public interface LaterRegistration {

    void registerLater(Registration api, Injector injector);

}
