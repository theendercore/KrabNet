package org.teamvoided.krabnet.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.krabnet.init.KNEntityTypes
import org.teamvoided.krabnet.init.KNItems
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.utils.playSound
import org.teamvoided.krabnet.utils.spawnParticles
import kotlin.math.min

class ConfettiBombEntity : ThrownItemEntity {
    constructor(entityType: EntityType<out ConfettiBombEntity>, world: World) : super(entityType, world)
    constructor(world: World) : super(KNEntityTypes.CONFETTI_BOMB, world)
    constructor(world: World, owner: LivingEntity) : super(KNEntityTypes.CONFETTI_BOMB, owner, world)

    override fun getDefaultItem(): Item = KNItems.CONFETTI_BOMB

    override fun onBlockHit(hitResult: BlockHitResult) {
        super.onBlockHit(hitResult)
        if (world.isClient) return
        (world as ServerWorld).doExploding { getVec(hitResult.side) }
        discard()
    }

    override fun onEntityHit(hitResult: EntityHitResult) {
        super.onEntityHit(hitResult)
        if (hitResult.entity is ProjectileEntity || world.isClient) return
        (world as ServerWorld).doExploding { randomVec() }
        discard()
    }

    private fun ServerWorld.doExploding(vec: () -> Vec3d) {
        this.playSound(pos, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.BLOCKS, 0.04f, 50.0f)
        repeat(360) {
            val velocity = vec.invoke().normalize().multiply(min(random.nextDouble() + 0.2, 1.3))
            this.spawnParticles(KNParticleTypes.CONFETTI, pos, velocity)
        }
    }

    private fun getVec(side: Direction): Vec3d {
        return when (side) {
            Direction.NORTH -> Vec3d(rand(), rand(), rand(-360, 15))
            Direction.SOUTH -> Vec3d(rand(), rand(), rand(15))
            Direction.EAST -> Vec3d(rand(15), rand(), rand())
            Direction.WEST -> Vec3d(rand(-360, 15), rand(), rand())
            Direction.UP -> Vec3d(rand(), rand(15), rand())
            Direction.DOWN -> Vec3d(rand(), rand(-360, 15), rand())
            else -> randomVec()
        }
    }

    private fun rand(min: Int = -360, max: Int = 360) = random.range(min, max + 1).toDouble()
    private fun randomVec() = Vec3d(rand(), rand(), rand())
}
