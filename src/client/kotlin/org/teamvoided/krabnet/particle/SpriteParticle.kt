package org.teamvoided.krabnet.particle

import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.texture.Sprite
import net.minecraft.client.world.ClientWorld

abstract class SpriteParticle : Particle {
    protected open var sprite: Sprite? = null

    protected constructor(world: ClientWorld, x: Double, y: Double, z: Double) : super(world, x, y, z)

    protected constructor(world: ClientWorld, x: Double, y: Double, z: Double, g: Double, h: Double, i: Double)
            : super(world, x, y, z, g, h, i)


    protected val minU: Float get() = sprite!!.minU
    protected val maxU: Float get() = sprite!!.maxU
    protected val minV: Float get() = sprite!!.minV
    protected val maxV: Float get() = sprite!!.maxV

    fun setSprite(spriteProvider: SpriteProvider) {
        this.sprite = spriteProvider.getRandom(this.random)
    }

    fun setSpriteForAge(spriteProvider: SpriteProvider) {
        if (!this.dead) {
            this.sprite = spriteProvider.getSprite(this.age, this.maxAge)
        }
    }
}