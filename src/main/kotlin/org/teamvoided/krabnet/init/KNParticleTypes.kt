package org.teamvoided.krabnet.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.krabnet.KrabNet.id

object KNParticleTypes {

    val CONFETTI = FabricParticleTypes.simple()
    fun init() {
        register("confetti", CONFETTI)
    }

    fun <T : ParticleEffect> register(name: String, particle: ParticleType<T>) =
        Registry.register(Registries.PARTICLE_TYPE, id(name), particle)
}