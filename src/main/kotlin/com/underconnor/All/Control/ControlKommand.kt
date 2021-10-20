package com.underconnor.All.Control

import com.underconnor.All.AllPluginMain
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.Plugin

object ControlKommand {
    private fun getInstance(): Plugin {
        return AllPluginMain.instance
    }
    private fun getConfig(): FileConfiguration {
        return getInstance().config
    }

    private fun already(sender:CommandSender, same:Boolean){
        sender.sendMessage(text("이미 값이 ${same}로 설정되어 있습니다.",NamedTextColor.RED))
    }

    private fun changeValue(value:String, sender: CommandSender, changeto:Boolean){
        if (getConfig().getBoolean(value) == changeto) already(sender, changeto)
        else {
            getConfig().set(value, changeto)
            getInstance().saveConfig()
            getInstance().reloadConfig()
        }
    }

    private fun checkValue(value: String, sender: CommandSender){
        sender.sendMessage(text("${ChatColor.GOLD}${value}${ChatColor.WHITE}는 ${ChatColor.GOLD}${getConfig().getBoolean(value)}${ChatColor.WHITE} 입니다."))
    }

    fun ControlKommand() {
        getInstance().kommand {
            register("control") {
                permission("all.control")

                executes { sender.sendMessage(text("Control System from ALL IN ONE plugin",NamedTextColor.GOLD)) }

                then("player") {
                    executes { sender.sendMessage(text("/control player <권한> <true/false>", NamedTextColor.RED)) }
                    then("canchat") {
                        executes { checkValue("canchat", sender) }
                        then("true") {executes { changeValue("canchat", sender, true) }}
                        then("false") {executes { changeValue("canchat", sender, false) }}
                    }
                    then("canmove") {
                        executes { checkValue("canmove", sender) }
                        then("true") {executes { changeValue("canmove", sender, true) }}
                        then("false") {executes { changeValue("canmove", sender, false) }}
                    }
                    then("cancommand") {
                        executes { checkValue("cancommand", sender) }
                        then("true") {executes { changeValue("cancommand", sender, true) }}
                        then("false") {executes { changeValue("cancommand", sender, false) }}
                    }
                    then("canpvp") {
                        executes { checkValue("canpvp", sender) }
                        then("true") {executes { changeValue("canpvp", sender, true) }}
                        then("false") {executes { changeValue("canpvp", sender, false) }}
                    }
                    then("caninteraction") {
                        executes { checkValue("caninteraction", sender) }
                        then("true") {executes { changeValue("caninteraction", sender, true) }}
                        then("false") {executes { changeValue("caninteraction", sender, false) }}
                    }
                    then("canblockbreak") {
                        executes { checkValue("canblockbreak", sender) }
                        then("true") {executes { changeValue("canblockbreak", sender, true) }}
                        then("false") {executes { changeValue("canblockbreak", sender, false) }}
                    }
                    then("canblockplace") {
                        executes { checkValue("canblockplace", sender) }
                        then("true") {executes { changeValue("canblockplace", sender, true) }}
                        then("false") {executes { changeValue("canblockplace", sender, false) }}
                    }
                    then("canpickitem") {
                        executes { checkValue("canpickitem", sender) }
                        then("true") {executes { changeValue("canpickitem", sender, true) }}
                        then("false") {executes { changeValue("canpickitem", sender, false) }}
                    }
                    then("candropitem") {
                        executes { checkValue("candropitem", sender) }
                        then("true") {executes { changeValue("candropitem", sender, true) }}
                        then("false") {executes { changeValue("candropitem", sender, false) }}
                    }
                    then("canjoin") {
                        executes { checkValue("canjoin", sender) }
                        then("true") {executes { changeValue("canjoin", sender, true) }}
                        then("false") {executes { changeValue("canjoin", sender, false) }}
                    }
                }
            }
        }
    }
}