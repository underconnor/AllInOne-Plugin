package com.underconnor.All.Control

import com.underconnor.All.AllPluginMain
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component.text
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.event.player.*
import org.bukkit.plugin.Plugin

class ControlEvent: Listener {
    private fun getInstance(): Plugin {
        return AllPluginMain.instance
    }

    private fun getConfig(): FileConfiguration {
        return getInstance().config
    }

    private val administrator = getConfig().getString("administrator").toString()

    @EventHandler
    fun onAsyncChat(event: AsyncChatEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canchat")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canmove")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("cancommand")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            if (administrator.contains(event.entity.uniqueId.toString())) return
            if (getConfig().getBoolean("canpvp")) return
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        var damager: Any = event.damager
        if (damager is Projectile) damager = damager.shooter ?: return

        if (damager is Player) {
            if (administrator.contains(damager.uniqueId.toString())) return
            if (getConfig().getBoolean("canpvp")) return
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("caninteraction")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerInteractEntity(event: PlayerInteractEntityEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("caninteraction")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canblockbreak")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onHangingBreakByEntity(event: HangingBreakByEntityEvent) {
        var remover: Any = event.remover ?: return
        if (remover is Projectile) remover = remover.shooter ?: return

        if (remover is Player) {
            if (administrator.contains(remover.uniqueId.toString())) return
            if (getConfig().getBoolean("canblockbreak")) return
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canblockplace")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onHangingPlace(event: HangingPlaceEvent) {
        val player = event.player ?: return

        if (administrator.contains(player.uniqueId.toString())) return
        if (getConfig().getBoolean("canblockplace")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerAttemptPickupItem(event: PlayerAttemptPickupItemEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canpickitem")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("candropitem")) return
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerLogin(event: PlayerLoginEvent) {
        if (administrator.contains(event.player.uniqueId.toString())) return
        if (getConfig().getBoolean("canjoin")) return
        event.player.kick(text("현재 서버 접속이 불가능합니다."))
    }
}