package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.manager.PlayerManager;

/**
 * SectionTask 的部分預設版本
 */
public abstract class AbstractSectionTask implements SectionTask {

    protected PlayerManager playerManager;
    protected boolean running = false;

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void initTimer(PlayerManager playerManager) {
        this.playerManager = playerManager;
        initRun(playerManager);
    }

    public abstract void initRun(PlayerManager playerManager);

    @Override
    public void setRunning(boolean b) {
        this.running = b;
    }
}
