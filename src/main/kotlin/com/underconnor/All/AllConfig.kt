package com.underconnor.All

import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import java.io.File

object AllConfig {
    @Config
    var administrator = arrayListOf(
        "b74b717f-d6b5-4336-8254-d3986648d062"
    )

    // 플레이 제어 Config 부분
    @Config
    var canchat = true
    @Config
    var canmove = true
    @Config
    var cancommand = true
    @Config
    var canpvp = true
    @Config
    var caninteraction = true
    @Config
    var canblockbreak = true
    @Config
    var canblockplace = true
    @Config
    var canpickitem = true
    @Config
    var candropitem = true
    @Config
    var canjoin = true

    @Config
    var motd = "ALL-IN-ONE"

    @Config
    var discord = ""
    @Config
    var twitch = ""

    fun load(configFile: File) {
        ConfigSupport.compute(this, configFile)
    }
}