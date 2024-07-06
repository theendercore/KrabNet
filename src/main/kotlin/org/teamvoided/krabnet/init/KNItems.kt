package org.teamvoided.krabnet.init

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.item.ConfettiBombItem
import org.teamvoided.krabnet.item.ConfettiStickItem
import org.teamvoided.krabnet.utils.register

object KNItems {
    val tabItems = mutableListOf<Item>()
    fun init() {}

    val CONFETTI_STICK = register("confetti_stick", ConfettiStickItem(Item.Settings()))
    val CONFETTI_BOMB = register("confetti_bomb", ConfettiBombItem(Item.Settings()))


    fun <T : Item> register(name: String, item: T): Item {
        val regItem = Registries.ITEM.register(id(name), item)
        tabItems.add(regItem)
        return regItem
    }
}