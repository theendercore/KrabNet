package org.teamvoided.krabnet.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNItems

class ConfettiBombEntity : ThrownItemEntity {
    constructor(entityType: EntityType<out ConfettiBombEntity>, world: World) : super(entityType, world)

    constructor(world: World, owner: LivingEntity) : super(KNEntityTypes.CONFETTI_BOMB, owner, world)

    constructor(world: World, x: Double, y: Double, z: Double) : super(KNEntityTypes.CONFETTI_BOMB, x, y, z, world)

    override fun getDefaultItem(): Item = KNItems.CONFETTI_BOMB

    override fun onCollision(hitResult: HitResult) {
        super.onCollision(hitResult)
        if (!this.world.isClient) {
            println("HIT")
        }
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (!this.world.isClient) {
            println("HIT")
        }
    }
}