package org.teamvoided.krabnet.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.teamvoided.krabnet.init.KNParticleTypes

class ConfettiStickItem(settings: Settings) : Item(settings) {

    override fun use(worldIn: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (!worldIn.isClient) {
            val world = worldIn as ServerWorld

            world.spawnParticles(
                KNParticleTypes.CONFETTI,
                player.x,
                player.y + player.height / 2,
                player.z,
                50,
                0.0,
                0.0,
                0.0,
                2.0
            )
        }

        return super.use(worldIn, player, hand)
    }
}