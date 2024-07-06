package org.teamvoided.krabnet.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNItems
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.utils.addParticle
import org.teamvoided.krabnet.utils.posOrNeg

class ConfettiBombEntity : ThrownItemEntity {
    constructor(entityType: EntityType<out ConfettiBombEntity>, world: World) : super(entityType, world)

    constructor(world: World, owner: LivingEntity) : super(KNEntityTypes.CONFETTI_BOMB, owner, world)

    constructor(world: World, x: Double, y: Double, z: Double) : super(KNEntityTypes.CONFETTI_BOMB, x, y, z, world)

    override fun getDefaultItem(): Item = KNItems.CONFETTI_BOMB

    override fun onCollision(hitResult: HitResult) {
        super.onCollision(hitResult)
        if (!world.isClient) {
            world.sendEntityStatus(this, 69);
            this.discard();
        }
    }

    override fun handleStatus(status: Byte) {
        if (status.toInt() == 69) {
            world.playSound(
                null, pos.x, pos.y + 1, pos.z,
                SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.MASTER,
                0.5f, 0.4f
            )
            repeat(360) {
                val velocity = Vec3d(
                    random.nextDouble() * 0.4 * random.posOrNeg(),
                    0.5,
                    random.nextDouble() * 0.4 * random.posOrNeg()
                ).normalize().multiply(random.nextDouble() * 0.5 + 0.3)
                world.addParticle(KNParticleTypes.CONFETTI, this.pos, velocity)
            }
        }
    }
}