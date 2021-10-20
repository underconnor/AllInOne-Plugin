package com.underconnor.All

import com.underconnor.All.announce.announceKommand
import com.underconnor.All.Control.ControlEvent
import com.underconnor.All.Control.ControlKommand
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class AllPluginMain : JavaPlugin() {

    companion object {
        lateinit var instance: AllPluginMain
            private set
    }

    private val configFile = File(dataFolder, "config.yml")

    override fun onEnable() {
        instance = this
        AllConfig.load(configFile)
        server.pluginManager.registerEvents(AllEvent(), this)
        server.pluginManager.registerEvents(ControlEvent(), this)
        AllKommand.MainKommand()
        ControlKommand.ControlKommand()
        announceKommand.announceKommand()
        logger.info("ALL IN ONE Plugin has loaded.")
    }

    override fun onDisable() {
        super.onDisable()
    }
}