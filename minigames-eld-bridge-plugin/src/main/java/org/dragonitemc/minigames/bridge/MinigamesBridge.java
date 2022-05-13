package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.eld.AddonManager;
import com.ericlam.mc.eld.ELDBukkitAddon;
import com.ericlam.mc.eld.ManagerProvider;
import com.ericlam.mc.eld.ServiceCollection;

public final class MinigamesBridge extends ELDBukkitAddon {

    @Override
    protected void bindServices(ServiceCollection serviceCollection) {
    }

    @Override
    protected void preAddonInstall(ManagerProvider provider, AddonManager installer) {
        ELDMinigameInstallation installation = new ELDMinigameInstallation();
        installer.customInstallation(MinigameInstallation.class, installation);
        MinigamesModule module = new MinigamesModule(installation);
        installer.installModule(module);
    }
}
