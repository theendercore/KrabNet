package org.teamvoided.krabnet

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.particle.FlameParticle
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.component.type.DyedColorComponent.getColorOrDefault
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import org.teamvoided.krabnet.KrabNet.log
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNItems
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.init.KNParticleTypes.EINSTEIN
import org.teamvoided.krabnet.particle.ConfettiParticle
import org.teamvoided.krabnet.utils.Vec3d
import org.teamvoided.krabnet.utils.addParticle
import java.util.*

@Suppress("unused")
object KrabNetClient {

    fun init() {
        log.info("Hello from Client")
        ParticleFactoryRegistry.getInstance().register(KNParticleTypes.CONFETTI, ConfettiParticle::ConfettiFactory)
        ParticleFactoryRegistry.getInstance().register(EINSTEIN, FlameParticle::Factory)
        /*  ParticleFactoryRegistry.getInstance()
              .register(KNParticleTypes.CONFETTI_EMITTER, ConfettiParticle::ConfettiEmitterFactory)*/

        ColorProviderRegistry.ITEM.register({ stack, _ -> getColorOrDefault(stack, 0xffffff) }, KNItems.PARTY_POPPER)
        EntityRendererRegistry.register(KNEntityTypes.CONFETTI_BOMB, ::FlyingItemEntityRenderer)

        if (FabricLoader.getInstance().isDevelopmentEnvironment) devMode()

        AttackEntityCallback.EVENT.register(::doAttackStuff)
    }

    private fun doAttackStuff(
        player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult?
    ): ActionResult {
        if (entity is PlayerEntity && entity.gameProfile.id == UUID.fromString("d71e4b41-9315-499f-a934-ca925421fb38")) {
            val random = world.random
            repeat(12) {
                val pos =
                    entity.pos.add(
                        (random.nextDouble() - 0.5) / 1.5,
                        1 + (random.nextDouble() - 0.5) * 2,
                        (random.nextDouble() - 0.5) / 1.5
                    )
                world.addParticle(EINSTEIN, pos, Vec3d(0), true)
            }
        }
        return ActionResult.PASS
    }

    private fun devMode() {
        Rendering.init()
    }
}
