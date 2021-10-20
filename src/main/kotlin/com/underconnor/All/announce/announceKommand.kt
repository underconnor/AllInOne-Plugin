package com.underconnor.All.announce

import com.underconnor.All.AllPluginMain
import io.github.monun.kommand.StringType
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.plugin.Plugin

object announceKommand {
    private fun getInstance(): Plugin {
        return AllPluginMain.instance
    }
    
    fun announceKommand() {
        getInstance().kommand {
            register("announce") {
                permission("all.announce")
                executes { sender.sendMessage(text("/announce <메시지> (&컬러코드 지원)", NamedTextColor.RED)) }
                then ("message" to string(StringType.GREEDY_PHRASE)){
                    executes {
                        val message: String by it
                        getInstance().server.sendMessage(text("\n${ChatColor.GRAY}${ChatColor.BOLD}--${ChatColor.RED}${ChatColor.BOLD}ANNOUNCEMENT${ChatColor.GRAY}----------${ChatColor.WHITE}\n" +
                                "${message.replace("&","§")}${ChatColor.WHITE}${ChatColor.RESET}\n" +
                                "${ChatColor.GRAY}${ChatColor.BOLD}------------------------"))
                    }
                }
            }
        }
    }
}