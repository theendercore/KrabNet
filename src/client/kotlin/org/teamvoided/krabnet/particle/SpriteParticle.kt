package org.teamvoided.krabnet.particle

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.texture.Sprite
import net.minecraft.client.world.ClientWorld
import org.joml.Quaternionf
import org.joml.Vector3f

abstract class SpriteParticle : Particle {
    protected open var sprite: Sprite? = null

    protected constructor(world: ClientWorld, x: Double, y: Double, z: Double) : super(world, x, y, z)

    protected constructor(world: ClientWorld, x: Double, y: Double, z: Double, g: Double, h: Double, i: Double)
            : super(world, x, y, z, g, h, i)


    protected val minU: Float get() = sprite!!.minU
    protected val maxU: Float get() = sprite!!.maxU
    protected val minV: Float get() = sprite!!.minV
    protected val maxV: Float get() = sprite!!.maxV

    @Suppress("LocalVariableName")
    fun VertexConsumer.drawVert(
        qRotation: Quaternionf,
        x: Float, y: Float, z: Float, x2: Float, y2: Float, scale: Float, U: Float, V: Float, light: Int,
        rot2: Quaternionf = Quaternionf()
    ) {
        val pos = Vector3f(x2, y2, 0.0f)
            .rotate(rot2)
            .rotate(qRotation)
            .mul(scale)
            .add(x, y, z)

        this.method_22912(pos.x(), pos.y(), pos.z())
            .method_22913(U, V)
            .method_22922(OverlayTexture.DEFAULT_UV)
            .method_22915(colorRed, colorGreen, colorBlue, colorAlpha)
            .method_60803(light)
    }

    fun setSprite(spriteProvider: SpriteProvider) {
        this.sprite = spriteProvider.getRandom(this.random)
    }

    fun setSpriteForAge(spriteProvider: SpriteProvider) {
        if (!this.dead) {
            this.sprite = spriteProvider.getSprite(this.age, this.maxAge)
        }
    }
}