package com.underconnor.All

import io.github.monun.kommand.StringType
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.plugin.Plugin

object AllKommand {
    private fun getInstance(): Plugin {
        return AllPluginMain.instance
    }
    
    fun MainKommand() {
        getInstance().kommand {
            register("all-in-one") {
                executes { sender.sendMessage(text("ALL IN ONE Plugin by underconnor",NamedTextColor.GOLD)) }
                then ("help"){
                permission("all.help")
                    executes { sender.sendMessage(text("${ChatColor.GOLD}/control ${ChatColor.WHITE}- 플레이어 제어\n" +
                            "${ChatColor.GOLD}/announce ${ChatColor.WHITE}- 공지 메시지 출력\n" +
                            "${ChatColor.GOLD}/setmotd ${ChatColor.WHITE}- 서버 MOTD설정\n" +
                            "${ChatColor.GOLD}/discord ${ChatColor.WHITE}- 디스코드 설정\n" +
                            "${ChatColor.GOLD}/twitch ${ChatColor.WHITE}- 트위치 설정"))}
                }
            }
            register("setmotd"){
                executes { sender.sendMessage(text("/setmotd <메시지> (&컬러코드 지원)", NamedTextColor.RED)) }
                then ("motd" to string(StringType.GREEDY_PHRASE)) {
                    executes {
                        val motd:String by it
                        getInstance().config.set("motd", motd.replace("&","§"))
                        getInstance().saveConfig()
                        getInstance().reloadConfig()
                        sender.sendMessage(text("서버의 MOTD가 ${ChatColor.GOLD}${motd}${ChatColor.WHITE}로 설정되었습니다."))
                    }
                }
            }
            register("discord"){
                executes {
                    val discord = getInstance().config.getString("discord")
                    if (discord == "") {
                        sender.sendMessage(text("연결된 디스코드 서버가 없습니다.",NamedTextColor.RED))
                    } else {
                        sender.sendMessage(text("Discord : ${discord}",NamedTextColor.GOLD))
                    }
                }
                then("set"){
                    then ("discord" to string(StringType.GREEDY_PHRASE)) {
                        executes {
                            val discord:String by it
                            getInstance().config.set("discord", discord)
                            getInstance().saveConfig()
                            getInstance().reloadConfig()
                            sender.sendMessage(text("서버의 디스코드가 ${ChatColor.GOLD}${discord}${ChatColor.WHITE}로 설정되었습니다."))
                        }
                    }
                }
            }
            register("twitch"){
                executes {
                    val twitch = getInstance().config.getString("twitch")
                    if (twitch == "") {
                        sender.sendMessage(text("연결된 트위치가 없습니다.",NamedTextColor.RED))
                    } else {
                        sender.sendMessage(text("Twitch : ${twitch}",NamedTextColor.BLUE))
                    }
                }
                then("set"){
                    then ("twitch" to string(StringType.GREEDY_PHRASE)) {
                        executes {
                            val twitch:String by it
                            getInstance().config.set("twitch", twitch)
                            getInstance().saveConfig()
                            getInstance().reloadConfig()
                            sender.sendMessage(text("트위치 주소가 ${ChatColor.GOLD}${twitch}${ChatColor.WHITE}로 설정되었습니다."))
                        }
                    }
                }
            }
        }
    }
}