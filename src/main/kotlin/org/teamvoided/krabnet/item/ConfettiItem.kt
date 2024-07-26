package org.teamvoided.krabnet.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import org.teamvoided.krabnet.init.KNDataComponents

open class ConfettiItem(settings: Settings) : Item(settings.component(KNDataComponents.CONFETTI_LEVEL, 1)) {


    override fun getItemBarColor(stack: ItemStack): Int = 0xffffff

    override fun getItemBarStep(stack: ItemStack): Int =
        stack.get(KNDataComponents.CONFETTI_LEVEL) ?: 0


//    override fun isItemBarVisible(stack: ItemStack): Boolean = getItemBarStep(stack) != 0

    fun getMaxConfettiLevel(): Int = 10
}