package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.minigames.core.game.GameTeam;
import com.ericlam.mc.minigames.core.registable.Voluntary;
import org.bukkit.inventory.ItemStack;

public final class ELDGameItemRegister implements GameItemRegister {

    private final Voluntary voluntary;

    public ELDGameItemRegister(Voluntary voluntary) {
        this.voluntary = voluntary;
    }

    @Override
    public void addGameItem(int slot, ItemStack itemStack) {
        voluntary.addGameItem(slot, itemStack);
    }

    @Override
    public void addGameItem(GameTeam team, int slot, ItemStack itemStack) {
        voluntary.addGameItem(team, slot, itemStack);
    }

    @Override
    public void addJoinItem(int slot, ItemStack itemStack) {
        voluntary.addJoinItem(slot, itemStack);
    }

    @Override
    public void addSpectatorItem(int slot, ItemStack itemStack) {
        voluntary.addSpectatorItem(slot, itemStack);
    }

    @Override
    public void addSpectatorItem(GameTeam team, int slot, ItemStack itemStack) {
        voluntary.addSpectatorItem(team, slot, itemStack);
    }
}
