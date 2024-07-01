package org.teamvoided.krabnet.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType

@Environment(EnvType.CLIENT)
class ConfettiParticle(world: ClientWorld, x: Double, y: Double, z: Double) : SpriteBillboardParticle(world, x, y, z) {
    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    @Environment(EnvType.CLIENT)
    class ConfettiFactory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            defaultParticleType: DefaultParticleType, world: ClientWorld,
            x: Double, y: Double, z: Double,
            xSpeed: Double, ySpeed: Double, zSpeed: Double
        ): Particle {
            val confetti = ConfettiParticle(world, x, y, z)
            confetti.setSprite(this.spriteProvider)
            return confetti
        }
    }
}