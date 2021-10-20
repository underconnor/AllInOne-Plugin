package com.underconnor.All

import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import io.github.monun.tap.effect.playFirework
import net.kyori.adventure.text.Component.text
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class AllEvent : Listener {
    private fun getInstance(): Plugin {
        return AllPluginMain.instance
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val loc = e.player.location.add(0.0, 0.5, 0.0)
        val firework = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build()
        loc.world.playFirework(loc, firework)
    }
    @EventHandler
    fun onPaperServerListPing(e: PaperServerListPingEvent) {
        val motd = getInstance().config.getString("motd")
        e.motd(text("${motd}"))
        e.playerSample.clear() // 플레이어 목록 안보이게
    }
}