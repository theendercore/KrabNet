package org.teamvoided.krabnet

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.component.type.DyedColorComponent
import org.teamvoided.krabnet.KrabNet.log
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNItems
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.particle.ConfettiParticle

@Suppress("unused")
object KrabNetClient {
    fun init() {
        log.info("Hello from Client")
        ParticleFactoryRegistry.getInstance().register(KNParticleTypes.CONFETTI, ConfettiParticle::ConfettiFactory)
        ParticleFactoryRegistry.getInstance()
            .register(KNParticleTypes.CONFETTI_EMITTER, ConfettiParticle::ConfettiEmitterFactory)

        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) },
            KNItems.PARTY_POPPER
        )

        EntityRendererRegistry.register(KNEntityTypes.CONFETTI_BOMB, ::FlyingItemEntityRenderer)
    }
}
