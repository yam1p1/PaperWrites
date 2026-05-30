package io.github.yam1p1.paperWrites

import io.github.yam1p1.paperWrites.command.writeCMD
import org.bukkit.plugin.java.JavaPlugin

class PaperWrites : JavaPlugin() {

    companion object {
        lateinit var plugin: PaperWrites
            private set
    }

    override fun onEnable() {
        // Plugin startup logic
        plugin = this

        writeCMD.register()
        MainLogic().init()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
