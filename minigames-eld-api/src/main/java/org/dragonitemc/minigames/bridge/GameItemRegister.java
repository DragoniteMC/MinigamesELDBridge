package org.dragonitemc.minigames.bridge;

import com.ericlam.mc.minigames.core.game.GameTeam;
import org.bukkit.inventory.ItemStack;

/**
 * 可以使用依賴注入的遊戲物品註冊器
 */
public interface GameItemRegister {

    /**
     * 與 Minigames-Core 的註冊相同。
     * @param slot 位置
     * @param itemStack 物品
     */
    void addGameItem(int slot, ItemStack itemStack);

    /**
     * 與 Minigames-Core 的註冊相同。
     * @param team 隊伍
     * @param slot 位置
     * @param itemStack 物品
     */
    void addGameItem(GameTeam team, int slot, ItemStack itemStack);

    /**
     * 與 Minigames-Core 的註冊相同。
     * @param slot 位置
     * @param itemStack 物品
     */
    void addJoinItem(int slot, ItemStack itemStack);

    /**
     * 與 Minigames-Core 的註冊相同。
     * @param slot 位置
     * @param itemStack 物品
     */
    void addSpectatorItem(int slot, ItemStack itemStack);

    /**
     * 與 Minigames-Core 的註冊相同。
     * @param team 隊伍
     * @param slot 位置
     * @param itemStack 物品
     */
    void addSpectatorItem(GameTeam team, int slot, ItemStack itemStack);

}
