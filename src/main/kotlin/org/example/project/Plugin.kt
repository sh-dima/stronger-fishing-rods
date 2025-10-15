package org.example.project

//import dev.jorel.commandapi.CommandAPI
//import dev.jorel.commandapi.CommandAPIBukkitConfig
//import org.bstats.bukkit.Metrics
//import de.exlll.configlib.YamlConfigurations
import org.bukkit.plugin.java.JavaPlugin
//import java.nio.file.Path

@Suppress("unused")
class Plugin : JavaPlugin() {

    private val pair = Pair("Hello World!", "Goodbye World!")
//    private lateinit var config: Config

    override fun onEnable() {
        logger.info(pair.first)

//        val configFile = Path.of(dataFolder.path, "config.yml")
//        config = try {
//            YamlConfigurations.load(configFile, Config::class.java)
//        } catch (e: Exception) {
//            Config()
//        }
//
//        YamlConfigurations.save(configFile, Config::class.java, config)

//        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
//        CommandAPI.onEnable()

//        try {
//            Metrics(this, https://bstats.org/what-is-my-plugin-id)
//        } catch (exception: Exception) {
//            exception.printStackTrace()
//        }
    }

    override fun onDisable() {
        logger.info(pair.second)
    }
}
