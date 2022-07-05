package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.eld.*;

@ELDBukkit(
        lifeCycle = MinigamesBridgeLifeCycle.class,
        registry = MinigamesBridgeRegistry.class
)
public final class MinigamesBridge extends ELDBukkitPlugin {

    @Override
    public void bindServices(ServiceCollection serviceCollection) {
        ELDMinigameInstallation installation = new ELDMinigameInstallation();
        AddonInstallation installer = serviceCollection.getInstallation(AddonInstallation.class);

        installer.customInstallation(MinigameInstallation.class, installation);
        MinigamesModule module = new MinigamesModule(installation);
        installer.installModule(module);
    }


    @Override
    protected void manageProvider(BukkitManagerProvider bukkitManagerProvider) {

    }
}
