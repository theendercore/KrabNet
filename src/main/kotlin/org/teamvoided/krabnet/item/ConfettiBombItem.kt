package org.teamvoided.krabnet.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.teamvoided.krabnet.entity.ConfettiBombEntity

class ConfettiBombItem(settings: Settings) : Item(settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        world.playSound(
            null, user.x, user.y, user.z,
            SoundEvents.ENTITY_WIND_CHARGE_THROW, SoundCategory.PLAYERS,
            0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f)
        )
        user.itemCooldownManager[this] = 10
        if (!world.isClient) {
            val confettiBomb = ConfettiBombEntity(world, user)
            confettiBomb.setItem(itemStack)
            confettiBomb.setProperties(user, user.pitch, user.yaw, 0.0f, 1.5f, 1.0f)
            world.spawnEntity(confettiBomb)
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.consume(1, user)
        return TypedActionResult.success(itemStack, world.isClient())
    }
}
