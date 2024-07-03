package org.teamvoided.krabnet.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.utils.registerHolder

object KNTabs {
    fun init() {}

    val KN_TAB = register(
        "krabnet", FabricItemGroup.builder()
            .icon { KNItems.CONFETTI_STICK.defaultStack }
            .name(Text.translatable("itemGroup.krabnet"))
            .entries { _, entires -> entires.addStacks(KNItems.tabItems.map(Item::getDefaultStack)) }
    )


    fun <T : ItemGroup.Builder> register(name: String, tab: T): Holder<ItemGroup> =
        Registries.ITEM_GROUP.registerHolder(id(name), tab.build())
}