package org.teamvoided.krabnet.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.teamvoided.krabnet.init.KNParticleTypes
import org.teamvoided.krabnet.utils.confettiLevel
import org.teamvoided.krabnet.utils.playSound
import org.teamvoided.krabnet.utils.spawnParticles
import kotlin.math.min

class ConfettiStickItem(settings: Settings) : ConfettiItem(settings)  {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        player.itemCooldownManager[this] = 2
        val confetti = stack.confettiLevel() ?: return TypedActionResult.pass(stack)
        if (!world.isClient) {
            val dir = player.getRotationVec(0f)
            val random = world.random
            val serverW = world as ServerWorld
            repeat(150 * confetti) {
                val newDir = dir
                    .add(random.nextDouble() - 0.5, random.nextDouble() - 0.1, random.nextDouble() - 0.5)
                    .normalize()
                    .multiply(min(random.nextDouble() + 0.2, 1.3))
                    .add(player.velocity)

                serverW.spawnParticles(KNParticleTypes.CONFETTI, player.pos.add(0.0, 1.5, 0.0), newDir)
            }
            serverW.playSound(player.pos, SoundEvents.BLOCK_COBWEB_BREAK, SoundCategory.PLAYERS, 4.0f, 8.0f)
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this))
        return TypedActionResult.success(stack, world.isClient())
    }
}
