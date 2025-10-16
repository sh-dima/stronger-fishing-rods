package io.gitlab.shdima.reel

import de.exlll.configlib.Comment
import de.exlll.configlib.Configuration

@Configuration
@Suppress("PropertyName")
class Config {
    @Comment("The strength that the fishing rod should have. Greater strength -> stronger pull")
    var strength = 0.4

    @Comment("Whether using a fishing rod on an entity should aggravate it\n" +
            "This also means that kill credit will be given for fishing rod kills")
    var `rod-aggression` = false
}
