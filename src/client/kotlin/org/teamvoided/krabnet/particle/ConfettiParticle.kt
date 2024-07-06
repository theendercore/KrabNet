package org.teamvoided.krabnet.particle

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.render.Camera
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import org.joml.Quaternionf
import org.teamvoided.krabnet.utils.rad
import org.teamvoided.krabnet.utils.stoppedByCollision

class ConfettiParticle(world: ClientWorld, x: Double, y: Double, z: Double) : SpriteParticle(world, x, y, z) {
    var scale = 0.1f

    var prevAngleX = 0f
    var angleX = random.range(0, 361).toFloat()
        set(value) {
            prevAngleX = field
            field = value
        }

    var prevAngleY = 0f
    var angleY = random.range(0, 361).toFloat()
        set(value) {
            prevAngleY = field
            field = value
        }

    var prevAngleZ = 0f
    var angleZ = random.range(0, 361).toFloat()
        set(value) {
            prevAngleZ = field
            field = value
        }

    val floorOffset = 0.01f * random.nextFloat()

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    init {
        this.gravityStrength = 0.345f + random.nextFloat() * 0.2f
        this.maxAge = random.range(60, 261)
        this.velocityX += random.nextDouble() * 0.02 * if (random.nextBoolean()) 1 else -1
        this.velocityY += random.nextDouble() * 0.02
        this.velocityZ += random.nextDouble() * 0.02 * if (random.nextBoolean()) 1 else -1
        this.colorRed = random.nextFloat() * 0.6f + 0.41f
        this.colorGreen = random.nextFloat() * 0.6f + 0.41f
        this.colorBlue = random.nextFloat() * 0.6f + 0.41f
        velocityMultiplier = 0.85f

        prevAngleX = angleX
        prevAngleY = angleY
        prevAngleZ = angleZ
    }

    override fun tick() {
        super.tick()
        if (!onGround) {
            angleX += 15f
            angleY += 15f
            angleZ += 15f

            this.stoppedByCollision(false)
        } else {
            angleX = 90f
            angleY = 0f
            prevAngleZ = angleZ
            if (age % 6 == 0) this.stoppedByCollision(false)

            // optional scale down on end of life, might be nice for diff thing
            /* val ageRatio = age / maxAge.toFloat()
             if (ageRatio > 0.85f) scale *= (ageRatio * ageRatio)
             if (scale < 0.001f) age = maxAge*/
        }
    }

    override fun buildGeometry(vertexConsumer: VertexConsumer, camera: Camera, delta: Float) {
        val qRotation = Quaternionf()
        if (angleX != 0f) qRotation.rotateX(MathHelper.lerp(delta, prevAngleX, angleX).rad)
        if (angleZ != 0f) qRotation.rotateZ(MathHelper.lerp(delta, prevAngleZ, angleZ).rad)
        if (angleY != 0f) qRotation.rotateY(MathHelper.lerp(delta, prevAngleY, angleY).rad)
        this.drawTranslatedFace(vertexConsumer, camera.pos, qRotation, delta)
    }

    private fun drawTranslatedFace(
        vertexConsumer: VertexConsumer, camPos: Vec3d, qRotation: Quaternionf, delta: Float
    ) {
        val x = (MathHelper.lerp(delta.toDouble(), prevPosX, x) - camPos.getX()).toFloat()
        val y = (MathHelper.lerp(delta.toDouble(), prevPosY, y) - camPos.getY() + floorOffset).toFloat()
        val z = (MathHelper.lerp(delta.toDouble(), prevPosZ, z) - camPos.getZ()).toFloat()
        this.drawFace(vertexConsumer, qRotation, x, y, z, delta)
        this.drawFace(vertexConsumer, qRotation, x, y, z, delta, Quaternionf().rotateLocalY(180.rad))
    }

    private fun drawFace(
        vertexConsumer: VertexConsumer, qRotation: Quaternionf, x: Float, y: Float, z: Float, delta: Float,
        rot2: Quaternionf = Quaternionf()
    ) {
        val light = this.getBrightness(delta)
        vertexConsumer.drawVert(qRotation, x, y, z, .5f, -1.0f, scale, maxU, maxV, light, rot2)
        vertexConsumer.drawVert(qRotation, x, y, z, .5f, 1.0f, scale, maxU, minV, light, rot2)
        vertexConsumer.drawVert(qRotation, x, y, z, -.5f, 1.0f, scale, minU, minV, light, rot2)
        vertexConsumer.drawVert(qRotation, x, y, z, -.5f, -1.0f, scale, minU, maxV, light, rot2)
    }

    class ConfettiFactory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            defaultParticleType: DefaultParticleType,
            world: ClientWorld,
            x: Double, y: Double, z: Double,
            xVelocity: Double, yVelocity: Double, zVelocity: Double
        ): Particle {
            val confetti = ConfettiParticle(world, x, y, z)
            confetti.setSprite(this.spriteProvider)
            confetti.velocityX += xVelocity
            confetti.velocityY += yVelocity
            confetti.velocityZ += zVelocity
            return confetti
        }
    }

    class ConfettiEmitterFactory(private val spriteProvider: SpriteProvider) : ParticleFactory<ConfettiEffect> {
        override fun createParticle(
            confettiEffect: ConfettiEffect,
            world: ClientWorld,
            x: Double, y: Double, z: Double,
            xVelocity: Double, yVelocity: Double, zVelocity: Double
        ): Particle {
            val confetti = ConfettiParticle(world, x, y, z)
            confetti.setSprite(this.spriteProvider)
            confetti.velocityX += xVelocity
            confetti.velocityY += yVelocity
            confetti.velocityZ += zVelocity
            return confetti
        }
    }
}
