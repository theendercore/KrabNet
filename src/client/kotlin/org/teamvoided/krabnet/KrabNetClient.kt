package org.teamvoided.krabnet

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.*
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.ShaderProgram
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.util.math.Vec3d
import org.joml.Matrix4f
import org.teamvoided.krabnet.KrabNet.id
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

        Rendering.init()
    }
}
