package org.teamvoided.krabnet

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import org.teamvoided.krabnet.KrabNet.log
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.particle.ConfettiParticle

@Suppress("unused")
object KrabNetClient{
    fun init() {
        log.info("Hello from Client")
        ParticleFactoryRegistry.getInstance().register(KNParticleTypes.CONFETTI, ConfettiParticle::ConfettiFactory)
        ParticleFactoryRegistry.getInstance().register(KNParticleTypes.CONFETTI_EMITTER, ConfettiParticle::ConfettiEmitterFactory)

        EntityRendererRegistry.register(KNEntityTypes.CONFETTI_BOMB, ::FlyingItemEntityRenderer)
    }
}
