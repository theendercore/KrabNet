package org.teamvoided.krabnet.particle

import com.mojang.blaze3d.vertex.VertexConsumer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.render.Camera
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper
import org.joml.Quaternionf
import org.joml.Vector3f

class ConfettiParticle(world: ClientWorld, x: Double, y: Double, z: Double) : SpriteParticle(world, x, y, z) {
    var scale = 0.1f
    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    init {
        this.gravityStrength = 0.1f
        this.maxAge = 80
        this.velocityX = random.nextDouble() * 0.07 * if (random.nextBoolean()) 1 else -1
        this.velocityY = random.nextDouble() * 0.1
        this.velocityZ = random.nextDouble() * 0.07 * if (random.nextBoolean()) 1 else -1
        this.colorRed = random.nextFloat() * 0.6f + 0.4f
        this.colorGreen = random.nextFloat() * 0.6f + 0.4f
        this.colorBlue = random.nextFloat() * 0.6f + 0.4f
    }

    override fun tick() {
        super.tick()
    }

    override fun buildGeometry(vertexConsumer: VertexConsumer, camera: Camera, delta: Float) {
        val qRotation = Quaternionf()
        if (this.angle != 0.0f) {
            qRotation.rotateZ(MathHelper.lerp(delta, prevAngle, angle))
        }
        this.drawTranslatedFace(vertexConsumer, camera, qRotation, delta)
    }

    private fun drawTranslatedFace(
        vertexConsumer: VertexConsumer,
        camera: Camera,
        qRotation: Quaternionf,
        delta: Float
    ) {
        val camPos = camera.pos
        val x = (MathHelper.lerp(delta.toDouble(), prevPosX, x) - camPos.getX()).toFloat()
        val y = (MathHelper.lerp(delta.toDouble(), prevPosY, y) - camPos.getY()).toFloat()
        val z = (MathHelper.lerp(delta.toDouble(), prevPosZ, z) - camPos.getZ()).toFloat()
        this.drawFace(vertexConsumer, qRotation, x, y, z, delta)
    }

    private fun drawFace(
        vertexConsumer: VertexConsumer,
        qRotation: Quaternionf,
        x: Float,
        y: Float,
        z: Float,
        delta: Float
    ) {
        val light = this.getBrightness(delta)
        vertexConsumer.drawVert(qRotation, x, y, z, 1.0f, -1.0f, scale, maxU, maxV, light)
        vertexConsumer.drawVert(qRotation, x, y, z, 1.0f, 1.0f, scale, maxU, minV, light)
        vertexConsumer.drawVert(qRotation, x, y, z, -1.0f, 1.0f, scale, minU, minV, light)
        vertexConsumer.drawVert(qRotation, x, y, z, -1.0f, -1.0f, scale, minU, maxV, light)
    }

    @Suppress("LocalVariableName")
    private fun VertexConsumer.drawVert(
        qRotation: Quaternionf,
        x: Float, y: Float, z: Float, x2: Float, y2: Float, scale: Float, U: Float, V: Float, light: Int
    ) {
        val pos = Vector3f(x2, y2, 0.0f)
            .rotate(qRotation)
            .mul(scale)
            .add(x, y, z)

        this.method_22912(pos.x(), pos.y(), pos.z())
            .method_22913(U, V)
            .method_22915(colorRed, colorGreen, colorBlue, colorAlpha)
            .method_60803(light)
    }


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