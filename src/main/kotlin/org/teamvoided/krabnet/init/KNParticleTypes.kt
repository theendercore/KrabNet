package org.teamvoided.krabnet.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.complex
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.simple
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.particle.ConfettiEffect

object KNParticleTypes {
    val CONFETTI = simple()
    val CONFETTI_EMITTER: ParticleType<ConfettiEffect> = complex(ConfettiEffect.CODEC, ConfettiEffect.PACKET_CODEC)

    val EINSTEIN = simple()

    fun init() {
        register("confetti", CONFETTI)
        register("einstein", EINSTEIN)
    }

    fun <T : ParticleEffect> register(name: String, particle: ParticleType<T>) =
        Registry.register(Registries.PARTICLE_TYPE, id(name), particle)
}