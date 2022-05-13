package org.dragonitemc.minigames.bridge;

import com.dragonite.mc.dnmc.core.managers.builder.AbstractInventoryBuilder;
import com.dragonite.mc.dnmc.core.misc.commands.DefaultCommand;
import com.ericlam.mc.minigames.core.SectionTask;
import com.ericlam.mc.minigames.core.arena.ArenaConfig;
import com.ericlam.mc.minigames.core.arena.ArenaMechanic;
import com.ericlam.mc.minigames.core.character.GamePlayerHandler;
import com.ericlam.mc.minigames.core.game.InGameState;
import com.ericlam.mc.minigames.core.gamestats.GameStatsHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

/**
 * 小遊戲註冊器的 ELDependenci 版本 (透過註冊 Class 來支援依賴注入)
 */
public interface MinigameInstallation {


    /**
     * 獲取強制註冊器 (性質跟 Minigames-Core 的 API 相同)
     *
     * @return 強制註冊器
     */
    Compulsory getCompulsory();

    /**
     * 獲取可選註冊器 (性質跟 Minigames-Core 的 API 相同)
     *
     * @return 可選註冊器
     */
    Voluntary getVoluntary();

    /**
     * 強制註冊器的 ELDependenci 版本 (透過註冊 Class 來支援依賴注入)
     */
    interface Compulsory {

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param defaultCommand 場地創建主指令 (可使用依賴注入)
         * @param plugin         指令所屬的插件
         */
        void registerArenaCommand(DefaultCommand defaultCommand, JavaPlugin plugin);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param arenaConfig 場地設定
         */
        void registerArenaConfig(Class<? extends ArenaConfig> arenaConfig);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param arenaMechanic 場地遊戲機制
         */
        void registerArenaMechanic(Class<? extends ArenaMechanic> arenaMechanic);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param task 遊戲結束程序
         */
        void registerEndTask(Class<? extends SectionTask> task);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param gamePlayerHandler 游戲玩家處理器
         */
        void registerGamePlayerHandler(Class<? extends GamePlayerHandler> gamePlayerHandler);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param gameStatsHandler 游戲統計處理器
         */
        void registerGameStatsHandler(Class<? extends GameStatsHandler> gameStatsHandler);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param task 大堂倒數程序
         */
        void registerLobbyTask(Class<? extends SectionTask> task);

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param builder 背包建造器
         * @param slots   地圖投票物品 slot
         */
        void registerVoteGUI(AbstractInventoryBuilder builder, Integer... slots);

    }

    /**
     * 可選註冊器的 ELDependenci 版本 (透過註冊 Class 來支援依賴注入)
     */
    interface Voluntary {

        /**
         * 性質與 Minigames-Core 的註冊相同。
         *
         * @param state 所屬場地狀態
         * @param task  所屬遊戲程序
         */
        void registerGameTask(Class<? extends InGameState> state, Class<? extends SectionTask> task);

        /**
         * 註冊遊戲物品註冊器
         * @param register 遊戲物品註冊器
         */
        void registerGameItem(Class<? extends Consumer<GameItemRegister>> register);

    }

}
