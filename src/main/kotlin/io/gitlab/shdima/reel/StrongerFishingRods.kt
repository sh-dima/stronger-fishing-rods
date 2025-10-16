package io.gitlab.shdima.reel

import org.bstats.bukkit.Metrics
import de.exlll.configlib.YamlConfigurations
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Path

@Suppress("unused")
class StrongerFishingRods : JavaPlugin(), Listener {

    private lateinit var config: Config

    override fun onEnable() {
        val configFile = Path.of(dataFolder.path, "config.yml")
        config = try {
            YamlConfigurations.load(configFile, Config::class.java)
        } catch (e: Exception) {
            Config()
        }

        YamlConfigurations.save(configFile, Config::class.java, config)

        try {
            Metrics(this, 27595)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    private fun onReel(event: PlayerFishEvent) {
        if (event.state != PlayerFishEvent.State.CAUGHT_ENTITY) return

        val entity = event.caught ?: return
        val targetLocation = entity.location

        val player = event.player
        val playerLocation = player.location

        val distance = playerLocation.distance(targetLocation)
        val difference = playerLocation.toVector().subtract(targetLocation.toVector())
        val direction = difference.normalize()
        val pullStrength = direction.multiply(config.strength * (distance / 20.0))

        entity.velocity = entity.velocity.add(pullStrength)
    }

    @EventHandler
    private fun onAggressiveReel(event: PlayerFishEvent) {
        if (!config.`rod-aggression`) return

        if (event.state != PlayerFishEvent.State.CAUGHT_ENTITY) return

        val entity = event.caught as? LivingEntity ?: return
        val player = event.player

        entity.damage(0.1, player)
        entity.health += 0.1
    }
}
