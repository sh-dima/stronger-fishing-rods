package io.gitlab.shdima.reel

import org.bstats.bukkit.Metrics
import de.exlll.configlib.YamlConfigurations
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Path

@Suppress("unused")
class StrongerFishingRods : JavaPlugin() {

    private lateinit var config: Config

    override fun onEnable() {
        val configFile = Path.of(dataFolder.path, "config.yml")
        config = try {
            YamlConfigurations.load(configFile, Config::class.java)
        } catch (e: Exception) {
            Config()
        }

//        YamlConfigurations.save(configFile, Config::class.java, config)

        try {
            Metrics(this, 27595)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
